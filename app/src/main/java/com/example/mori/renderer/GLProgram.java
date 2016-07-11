package com.example.mori.renderer;

import android.opengl.GLES20;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstração de Programa da biblioteca gráfica.
 * Created by mori on 09/07/16.
 */
public class GLProgram {

    private final int arrayIndex;
    private final ArrayList<GLAttribute> attributes;
    private final ArrayList<GLUniform> uniforms;
    private final int mode;
    private final int first;
    private final int count;
    private final Buffer indices;

    private int program;
    private final int[] programInfo;
    private HashMap<String, Integer> attributeLocation;
    private HashMap<String, Integer> uniformLocation;
    private int[] attributeType;

    public final int GL_ACTIVE_ATTRIBUTES = 0;
    public final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 1;
    public final int GL_ACTIVE_UNIFORMS = 2;
    public final int GL_ACTIVE_UNIFORM_MAX_LENGTH = 3;
    public final int GL_LINK_STATUS = 4;
    public final int GL_ATTACHED_SHADERS = 5;


    public GLProgram(int arrayIndex, ArrayList<GLAttribute> attributes,
                     ArrayList<GLUniform> uniforms, String vertexShaderCode,
                     String fragmentShaderCode, int mode, int first, int count, Buffer indices) {

        this.arrayIndex = arrayIndex;
        this.attributes = attributes;
        this.uniforms = uniforms;
        this.mode = mode;
        this.first = first;
        this.count = count;
        this.indices = indices;
        this.programInfo = new int[6];  /* 6 é o número de MACROS */

        initProgram(vertexShaderCode, fragmentShaderCode);
        initProgramInfo();
        initAttributeLocation();
        initUniformLocation();

        if (programInfo[GL_LINK_STATUS] == GLES20.GL_FALSE) {
            throw new RuntimeException("Link error: attached shader: " + programInfo[GL_ATTACHED_SHADERS]);
        }
    }

    private void initUniformLocation() {

        byte[] name = new byte[programInfo[GL_ACTIVE_UNIFORM_MAX_LENGTH]];
        int[] length = new int[1];
        int[] size = new int[1];
        int[] uniformType = new int[1];
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

    private void initProgramInfo() {

        GL.glGetProgramiv(program, GLES20.GL_ACTIVE_ATTRIBUTES, programInfo, GL_ACTIVE_ATTRIBUTES);
        GL.glGetProgramiv(program, GLES20.GL_ACTIVE_ATTRIBUTE_MAX_LENGTH,
                programInfo, GL_ACTIVE_ATTRIBUTE_MAX_LENGTH);
        GL.glGetProgramiv(program, GLES20.GL_ACTIVE_UNIFORMS, programInfo, GL_ACTIVE_UNIFORMS);
        GL.glGetProgramiv(program, GLES20.GL_ACTIVE_UNIFORM_MAX_LENGTH, programInfo,
                GL_ACTIVE_UNIFORM_MAX_LENGTH);
        GL.glGetProgramiv(program, GLES20.GL_LINK_STATUS, programInfo,
                GL_LINK_STATUS);
        GL.glGetProgramiv(program, GLES20.GL_ATTACHED_SHADERS, programInfo,
                GL_ATTACHED_SHADERS);
    }

    private void initProgram(String vertexShader, String fragmentShader) {

        program = GL.glCreateProgram();
        if (program == 0)
            throw new RuntimeException("Falha ao criar o programa");
        GL.glAttachShader(program, loadShader(GLES20.GL_VERTEX_SHADER, vertexShader));
        GL.glAttachShader(program, loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader));
        GL.glLinkProgram(program);
    }

    private int loadShader(int type, String shaderCode){

        int shader = GL.glCreateShader(type);
        GL.glShaderSource(shader, shaderCode);
        GL.glCompileShader(shader);
        return shader;
    }

    private void define() {

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
        for (GLUniform uniform :
                uniforms) {
            int location = uniformLocation.get(uniform.getName());
            if ((uniform.getName() != null) & (uniform.getArray() != null)
                    & (uniform.getCount() >= 0) & (uniform.getOffset() >= 0)) {

                GL.glUniform4fv(location, uniform.getCount(), uniform.getArray(),
                        uniform.getOffset());
            }
            else {
                throw new RuntimeException("Caso não implementado");
            }
        }
    }

    public void render(GlBuffers buffers) {
        GL.glUseProgram(program);
        buffers.bindArrayBuffer(arrayIndex);
        define();
        if (first == -1 & indices != null)
            GL.glDrawElements(mode, count, GLES20.GL_UNSIGNED_SHORT, indices);
        else
            GL.glDrawArrays(mode, first, count);
    }
}
