package com.example.mori.renderer;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Menu principal do app.
 * Created by mori on 06/07/16.
 */
public class Renderer implements GLSurfaceView.Renderer{
    private GLImage[] images;
    private int bufferSize = 0;
    private int texturesSize = 0;
    private GLBuffers buffers = null;
    private GLTextures textures;

    public Renderer(GLImage... images) {
        for (GLImage image :
                images) {
            if (image.getArray() != null) {
                image.setArrayIndex(bufferSize++);
            }
            if (image.getElementArray() != null) {
                image.setElementArrayIndex(bufferSize++);
            }
            if (image.getVertexShaderCode() == null | image.getFragmentShaderCode() == null) {
                throw new RuntimeException("Shader deve ser configurado. " +
                        "Utilize o método setShader da class GLImage!");
            }
        }

        for (GLImage image :
                images) {
            image.setTextureIndex(texturesSize++);
        }

        this.images = images;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        loadBuffers();
        loadUnits();
        loadShaderProgram();
    }

    private void loadUnits() {
        textures = new GLTextures(images, texturesSize);
    }

    private void loadBuffers() {
        buffers = new GLBuffers(images, bufferSize);
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
            image.render(buffers, textures);
        }
    }
}
