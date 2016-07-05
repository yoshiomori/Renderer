package com.example.mori.mainmenu;


import android.app.Activity;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView screen = new GLSurfaceView(this);
        screen.setRenderer(new mainMenu());
        setContentView(screen);
    }

    private class mainMenu implements GLSurfaceView.Renderer {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            float[] vertice = new float[]{0.0f, 0.0f};
            int[] buffers = new int[1];
            GLES20.glGenBuffers(buffers.length, buffers, 0);
            checkGlError("glGenBuffers");
            GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[0]);
            checkGlError("glBindBuffer");
            Buffer buffer = adaptData(vertice);
            // ref:
            // https://github.com/libgdx/libgdx/blob/master/gdx/src/com/badlogic/gdx/graphics
            // /glutils/VertexBufferObject.java
            GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, buffer.limit(), buffer,
                    GLES20.GL_STATIC_DRAW);
            checkGlError("glBufferData");
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {

        }

        @Override
        public void onDrawFrame(GL10 gl) {

        }
    }

    private void checkGlError(String glOperation) {
        int error;
        //noinspection LoopStatementThatDoesntLoop
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(this.toString(), glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

    private FloatBuffer adaptData(float[] array) {
        // Ref:
        // https://github.com/ehsan/opengles-book-samples/blob/master/Android/Ch10_MultiTexture/src/com/openglesbook/multitexture/MultiTextureRenderer.java
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(array.length * Float.SIZE);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(array);
        floatBuffer.position(0);
        return floatBuffer;
    }
}
