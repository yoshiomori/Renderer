package com.example.mori.renderer;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Menu principal do app.
 * Created by mori on 06/07/16.
 */
public class Renderer implements GLSurfaceView.Renderer{
    // Dados brutos
    private GLImage[] images;
    private int bufferSize;

    // Dados processados
    private GlBuffers buffers;

    public Renderer(GLImage[] images, int bufferSize) {
        this.images = images;
        this.bufferSize = bufferSize;
        this.buffers = null;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        loadBuffers();
        loadShaderProgram();
    }

    private void loadBuffers() {
        buffers = new GlBuffers(images, bufferSize);
    }

    private void loadShaderProgram() {
        for (GLImage image:
                images) {
            image.initProgram();
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GL.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        for (GLImage image : images) {
            image.render(buffers);
        }
    }
}
