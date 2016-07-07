package com.example.mori.mainmenu;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Menu principal do app.
 * Created by mori on 06/07/16.
 */
public class MainMenu implements GLSurfaceView.Renderer{
    private int program;
    private int vPosition;
    private GlBufferObjects glBufferObjects;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        loadVertices();
        loadShaderProgram();
    }

    private void loadShaderProgram() {
        program = GL.glCreateProgram();
        GL.glAttachShader(program, loadShader(GL.GL_VERTEX_SHADER, "/* Vertex Shader */" +
                "attribute vec2 vPosition;" +
                "void main() {" +
                "  gl_Position = vec4(vPosition, 0, 1);" +
                "  gl_PointSize = 50;" +
                "}"));
        GL.glAttachShader(program, loadShader(GL.GL_FRAGMENT_SHADER, "/* Fragment Shader */" +
                "precision mediump float;" +
                "void main() {" +
                "  gl_FragColor = vec4(1.0, 1.0, 1.0, 1.0);" +
                "}"));
        GL.glLinkProgram(program);

        vPosition = GL.glGetAttribLocation(program, "vPosition");
    }

    private int loadShader(int type, String shaderCode){
        int shader = GL.glCreateShader(type);
        GL.glShaderSource(shader, shaderCode);
        GL.glCompileShader(shader);
        return shader;
    }

    private void loadVertices() {
        glBufferObjects = new GlBufferObjects(2);
        glBufferObjects.set(0, new float[]{0.0f, 0.0f});
        glBufferObjects.set(1, new float[]{0.5f, 0.5f});
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GL.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        GL.glUseProgram(program);

        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, glBufferObjects.get(0));
        GL.glVertexAttribPointer(vPosition, 2, GL.GL_FLOAT, false, 0, 0);
        GL.glEnableVertexAttribArray(vPosition);

        GL.glDrawArrays(GL.GL_POINTS, 0, 1);

//        if(attribSize2 != attribLocations2.size()){
//            Log.e(this.toString(), "onDrawFrame: Attributes not initialized.");
//            throw new RuntimeException("onDrawFrame: Attributes not initialized.");
//        }
//        GL.glUseProgram(program2);
//        for(int attribPosition : attribLocations2)
//            GL.glEnableVertexAttribArray(attribPosition);
//        GL.glDrawArrays(GL.GL_POINTS, 0, 1);
    }
    class GlProgramObject extends ErrorCondition {
        private int program;
        private int buffSize;
        private int attributeBuffer;
        private int numAttributes;
        private int[] attribLocations;
        private int[] sizes;
        private int[] types;
        private int[] strides;
        private int[] offsets;

        public GlProgramObject (String vertexShader, String fragmentShader) {
            program = GL.glCreateProgram();
            GL.glAttachShader(program, loadShader(GL.GL_VERTEX_SHADER, vertexShader));
            GL.glAttachShader(program, loadShader(GL.GL_FRAGMENT_SHADER, fragmentShader));
            GL.glLinkProgram(program);
            buffSize = GL.getAttributeMaxLength(program);
        }

        public void setAttributes(int buffer, String[] names, int[] strides, int[] offsets) {
            attributeBuffer = buffer;
            numAttributes = GL.countActiveAttribute(program);
            e(numAttributes != names.length | numAttributes != strides.length | numAttributes
                    != offsets.length, this.toString(),
                    "setAttributes: Attributes not initialized.");
            attribLocations = new int[numAttributes];
            sizes = new int[numAttributes];
            types = new int[numAttributes];
            for (int i = 0; i < numAttributes; i++) {
                attribLocations[i] = GL.glGetAttribLocation(program, names[i]);
                int[] info = new int[1];
                byte[] info1 = new byte[buffSize];
                GLES20.glGetActiveAttrib(program, attribLocations[i], buffSize, info, 0, sizes, i,
                        types, i, info1, 0);
                sizes[i] /= types[i];
            }
            this.strides = strides;
            this.offsets = offsets;
        }

        public void draw(){
            GL.glUseProgram(program);
            defineAttributes();
            GL.glDrawArrays(GL.GL_POINTS, 0, 1);
        }

        private void defineAttributes(){
            GL.glBindBuffer(GL.GL_ARRAY_BUFFER, attributeBuffer);
            for (int i = 0; i < numAttributes; i++) {
                GL.glVertexAttribPointer(attribLocations[i], sizes[i], types[i], false, strides[i],
                        offsets[i]);
                GL.glEnableVertexAttribArray(attribLocations[i]);
            }
        }
    }

    private class GlBufferObjects {
        private int[] buffers;
        private int[] sizes;

        public GlBufferObjects(int n) {
            buffers = new int[n];
            sizes = new int[n];
            GL.glGenBuffers(n, buffers, 0);
        }

        public void set(int index, float[] data) {
            FloatBuffer buffer = adaptData(data);
            GL.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers[index]);
            // ref:
            // https://github.com/libgdx/libgdx/blob/master/gdx/src/com/badlogic/gdx/graphics
            // /glutils/VertexBufferObject.java
            GL.glBufferData(GL.GL_ARRAY_BUFFER, buffer.limit(), buffer, GL.GL_STATIC_DRAW);
            sizes[index] = Float.SIZE;
        }

        private FloatBuffer adaptData(float[] array) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(array.length * Float.SIZE);
            byteBuffer.order(ByteOrder.nativeOrder());
            FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
            floatBuffer.put(array);
            floatBuffer.position(0);
            return floatBuffer;
        }

        public int get(int index) {
            return buffers[index];
        }
    }
}
