package com.example.mori.renderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstração de dados usados para renderizar com a biblioteca gráfica.
 * Created by mori on 09/07/16.
 */
public abstract class GLImage {

    private int arrayIndex = -1;
    private int elementArrayIndex = -1;
    private ArrayList<GLAttribute> attributes = new ArrayList<>();
    private float[] array = null;
    private short[] elementArray = null;
    private ArrayList<GLUniform> uniforms = new ArrayList<>();
    private String fragmentShaderCode = null;
    private String vertexShaderCode = null;
    private int mode = -1;
    private int first = -1;
    private Buffer indices = null;
    private int count = -1;
    private int[] uniformType = null;
    private Bitmap bitmap = null;
    private int textureIndex = -1;
    private Context context;
    private HashMap<String, Integer> attributeIndexes = new HashMap<>();
    private HashMap<String, Integer> uniformIndexes = new HashMap<>();

    protected GLImage(Context context) {
        this.context = context;
    }

    public void setTexture(String name, int id) {
        setUniform(name, 0);
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), id);
    }

    protected void setAttribute(String name, Boolean normalized, int stride, int offset){
        if (attributeIndexes.containsKey(name)) {
            GLAttribute attribute = attributes.get(attributeIndexes.get(name));
            attribute.setNormalized(normalized);
            attribute.setStride(stride);
            attribute.setOffset(offset);
        }
        else {
            attributeIndexes.put(name, attributes.size());
            attributes.add(new GLAttribute(name, normalized, stride, offset));
        }
    }

    protected void setAttribute(String name, Boolean normalized, int stride, float[] array){
        if (attributeIndexes.containsKey(name)) {
            GLAttribute attribute = attributes.get(attributeIndexes.get(name));
            attribute.setNormalized(normalized);
            attribute.setStride(stride);
            attribute.setArray(array);
        }
        else {
            attributeIndexes.put(name, attributes.size());
            attributes.add(new GLAttribute(name, normalized, stride, array));
        }
    }

    protected void setUniform(String name, float[] array){
        if (uniformIndexes.containsKey(name)) {
            GLUniform uniform = uniforms.get(uniformIndexes.get(name));
            uniform.setArray(array);
        }
        else {
            uniformIndexes.put(name, uniforms.size());
            uniforms.add(new GLUniform(name, array));
        }
    }

    protected void setUniform(String name, int x){
        if (uniformIndexes.containsKey(name)) {
            GLUniform uniform = uniforms.get(uniformIndexes.get(name));
            uniform.setX(x);
        }
        else {
            uniformIndexes.put(name, uniforms.size());
            uniforms.add(new GLUniform(name, x));
        }
    }

    protected void setArray(float[] array) {
        assert array != null;
        this.array = array;
    }

    protected void setElementArray(short[] elementArray) {
        assert elementArray != null;
        this.elementArray = elementArray;
    }

    /**
     * Quando o element array buffer é declarado, o argumento first é usado como offset
     * @param mode modo de desenho
     * @param first ou offset, quando element array buffer é declarado
     * @param count número de vértices a ser desenhado.
     */
    protected void setShader(String vertexShaderCode, String fragmentShaderCode, int mode, int first, int count) {
        this.vertexShaderCode = vertexShaderCode;
        this.fragmentShaderCode = fragmentShaderCode;
        this.mode = mode;
        this.first = first;
        this.count = count;
    }

    protected void setShader(String vertexShaderCode, String fragmentShaderCode, int mode, int count,  short[] indices) {
        this.vertexShaderCode = vertexShaderCode;
        this.fragmentShaderCode = fragmentShaderCode;
        this.mode = mode;
        this.count = count;
        this.indices = GLBuffers.adapt(indices);
    }

    public String getFragmentShaderCode() {
        return fragmentShaderCode;
    }

    public String getVertexShaderCode() {
        return vertexShaderCode;
    }


    public int getArrayIndex() {
        return arrayIndex;
    }

    public void setArrayIndex(int arrayIndex){
        this.arrayIndex = arrayIndex;
    }

    public float[] getArray() {
        return array;
    }

    public short[] getElementArray() {
        return elementArray;
    }

    public void setElementArrayIndex(int elementArrayIndex) {
        this.elementArrayIndex = elementArrayIndex;
    }

    public int getElementArrayIndex() {
        return elementArrayIndex;
    }

    private int program;
    private HashMap<String, Integer> attributeLocation;
    private HashMap<String, Integer> uniformLocation;
    private int[] attributeType;
    private final int[] programInfo = new int[6]; /* 6 é o número de MACROS */

    private final int GL_ACTIVE_ATTRIBUTES = 0;
    private final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 1;
    private final int GL_ACTIVE_UNIFORMS = 2;
    private final int GL_ACTIVE_UNIFORM_MAX_LENGTH = 3;

    public void initProgram() {

        program = GL.glCreateProgram();
        if (program == 0)
            throw new RuntimeException("Falha ao criar o programa");
        GL.glAttachShader(program, loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode));
        GL.glAttachShader(program, loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode));
        GL.glLinkProgram(program);

        GL.glGetProgramiv(program, GLES20.GL_ACTIVE_ATTRIBUTES, programInfo, GL_ACTIVE_ATTRIBUTES);
        GL.glGetProgramiv(program, GLES20.GL_ACTIVE_ATTRIBUTE_MAX_LENGTH,
                programInfo, GL_ACTIVE_ATTRIBUTE_MAX_LENGTH);
        GL.glGetProgramiv(program, GLES20.GL_ACTIVE_UNIFORMS, programInfo, GL_ACTIVE_UNIFORMS);
        GL.glGetProgramiv(program, GLES20.GL_ACTIVE_UNIFORM_MAX_LENGTH, programInfo,
                GL_ACTIVE_UNIFORM_MAX_LENGTH);
        int GL_LINK_STATUS = 4;
        GL.glGetProgramiv(program, GLES20.GL_LINK_STATUS, programInfo,
                GL_LINK_STATUS);
        int GL_ATTACHED_SHADERS = 5;
        GL.glGetProgramiv(program, GLES20.GL_ATTACHED_SHADERS, programInfo,
                GL_ATTACHED_SHADERS);


        if (programInfo[GL_LINK_STATUS] == GLES20.GL_FALSE) {
            throw new RuntimeException("Link error: attached shader: " + programInfo[GL_ATTACHED_SHADERS]);
        }

        initAttributeLocation();
        initUniformLocation();
    }

    private int loadShader(int type, String shaderCode){

        int shader = GL.glCreateShader(type);
        GL.glShaderSource(shader, shaderCode);
        GL.glCompileShader(shader);
        return shader;
    }

    private void initUniformLocation() {

        byte[] name = new byte[programInfo[GL_ACTIVE_UNIFORM_MAX_LENGTH]];
        int[] length = new int[1];
        int[] size = new int[1];
        uniformType = new int[programInfo[GL_ACTIVE_UNIFORMS]];
        uniformLocation = new HashMap<>(programInfo[GL_ACTIVE_UNIFORMS]);
        for (int index = 0; index < programInfo[GL_ACTIVE_UNIFORMS]; index++) {
            GL.glGetActiveUniform(program, index, name.length, length, 0, size, 0, uniformType,
                    index, name, 0);
            uniformLocation.put(new String(name, 0, length[0]), index);
        }
    }

    private void initAttributeLocation() {

        byte[] name = new byte[programInfo[GL_ACTIVE_ATTRIBUTE_MAX_LENGTH]];
        int[] length = new int[1];
        int[] size = new int[1];
        attributeType = new int[programInfo[GL_ACTIVE_ATTRIBUTES]];
        attributeLocation = new HashMap<>(programInfo[GL_ACTIVE_ATTRIBUTES]);
        for (int index = 0; index < programInfo[GL_ACTIVE_ATTRIBUTES]; index++) {
            GL.glGetActiveAttrib(program, index, name.length, length, 0, size, 0, attributeType, index, name, 0);
            attributeLocation.put(new String(name, 0, length[0]), index);
        }
    }

    public void render(GLBuffers buffers, GLTextures textures) {
        GL.glUseProgram(program);
        buffers.bindArrayBuffer(arrayIndex);
        textures.bindTextures(textureIndex);
        defineAttributes();
        defineUniforms();
        if (indices != null) {
            GL.glDrawElements(mode, count, GLES20.GL_UNSIGNED_SHORT, indices);
        }
        else if (elementArrayIndex >= 0){
            buffers.bindElementArrayBuffer(elementArrayIndex);
            GL.glDrawElements(mode, count, GLES20.GL_UNSIGNED_SHORT, first);
            buffers.unbindElementArrayBuffer();
        }
        else {
            GL.glDrawArrays(mode, first, count);
        }
    }

    private void defineAttributes() {
        for (GLAttribute attribute :
                attributes) {
            int indx = attributeLocation.get(attribute.getName());

            /* -1 é não implementado */
            int size = attributeType[indx] == GLES20.GL_FLOAT_VEC4 ? 4
                    : attributeType[indx] == GLES20.GL_FLOAT_VEC3 ? 3
                    : attributeType[indx] == GLES20.GL_FLOAT_VEC2 ? 2
                    : -1;
            int type = attributeType[indx] == GLES20.GL_FLOAT_VEC4
                    | attributeType[indx] == GLES20.GL_FLOAT_VEC3
                    | attributeType[indx] == GLES20.GL_FLOAT_VEC2 ? GLES20.GL_FLOAT
                    : -1;

            if (size == -1 | type == -1)
                throw new RuntimeException("Tipo do attribute no vertex shader não implementado.");

            if (attribute.getOffset() >= 0) {

                GL.glVertexAttribPointer(indx, size, type, attribute.getNormalized(),
                        attribute.getStride() * size, attribute.getOffset() * size);

            }
            else if (attribute.getArray() != null) {
                GL.glVertexAttribPointer(indx, size, type, attribute.getNormalized(),
                        attribute.getStride() * size,
                        attribute.getArray());

            } else {
                throw new RuntimeException("Attribute não inicializado");
            }


            GL.glEnableVertexAttribArray(indx);
        }
    }

    private void defineUniforms() {
        for (GLUniform uniform :
                uniforms) {
            int location = uniformLocation.get(uniform.getName());
            if (uniformType[location] == GLES20.GL_FLOAT_VEC4) {
                GL.glUniform4fv(location, 1, uniform.getArray(), 0);
            }
            else if (uniformType[location] == GLES20.GL_FLOAT_MAT3) {
                GLES20.glUniformMatrix3fv(location, 1, false, uniform.getArray(), 0);
            }
            else if (uniformType[location] == GLES20.GL_FLOAT_MAT4) {
                GLES20.glUniformMatrix4fv(location, 1, false, uniform.getArray(), 0);
            }
            else if (uniformType[location] == GLES20.GL_SAMPLER_2D) {
                GLES20.glUniform1i(location, uniform.getX());
            }
            else {
                throw new RuntimeException("Caso não implementado");
            }
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setTextureIndex(int textureIndex) {
        this.textureIndex = textureIndex;
    }

    public int getTextureIndex() {
        return textureIndex;
    }
}
