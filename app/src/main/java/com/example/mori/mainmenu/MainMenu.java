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
    private int[] buffers;
    private int mPositionHandle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        loadVertices();
        loadShaderProgram();
    }

    private void loadShaderProgram() {
        program = GL.glCreateProgram();
        GL.glAttachShader(program, loadShader(GLES20.GL_VERTEX_SHADER,
                "attribute vec2 vPosition;" +
                        "void main() {" +
                        "  gl_Position = vec4(vPosition, 0, 1);" +
                        "  gl_PointSize = 50;" +
                        "}"));
        GL.glAttachShader(program, loadShader(GLES20.GL_FRAGMENT_SHADER,
                "precision mediump float;" +
                        "void main() {" +
                        "  gl_FragColor = vec4(1.0, 1.0, 1.0, 1.0);" +
                        "}"));
        GL.glLinkProgram(program);
        mPositionHandle = GL.glGetAttribLocation(program, "vPosition");
        GL.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[0]);
        GL.glVertexAttribPointer(mPositionHandle, 2, GLES20.GL_FLOAT, false, 2 * 4, 0);
    }

    private int loadShader(int type, String shaderCode){
        int shader = GL.glCreateShader(type);
        GL.glShaderSource(shader, shaderCode);
        GL.glCompileShader(shader);
        return shader;
    }

    private void loadVertices() {
        float[] vertice = new float[]{0.0f, 0.0f};
        buffers = new int[1];
        GL.glGenBuffers(buffers.length, buffers, 0);
        GL.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[0]);
        FloatBuffer buffer = adaptData(vertice);
        // ref:
        // https://github.com/libgdx/libgdx/blob/master/gdx/src/com/badlogic/gdx/graphics
        // /glutils/VertexBufferObject.java
        GL.glBufferData(GLES20.GL_ARRAY_BUFFER, buffer.limit(), buffer, GLES20.GL_STATIC_DRAW);
    }

    private FloatBuffer adaptData(float[] array) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(array.length * Float.SIZE);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(array);
        floatBuffer.position(0);
        return floatBuffer;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GL.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GL.glUseProgram(program);
        GL.glEnableVertexAttribArray(mPositionHandle);
        GL.glDrawArrays(GLES20.GL_POINTS, 0, 1);
    }
}
