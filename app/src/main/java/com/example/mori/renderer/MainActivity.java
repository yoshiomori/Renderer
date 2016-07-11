package com.example.mori.renderer;


import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GLImage[] images = new GLImage[]{
                new DotImage(),
                new TrianglesImage(),
                new DoisPontos(),
                new SquareImage(),
        };

        int bufferSize = 0;
        for (GLImage image :
                images) {
            if (image.getArray() != null) {
                image.setArrayIndex(bufferSize++);
            }
            if (image.getElementArray() != null) {
                image.setElementArrayIndex(bufferSize++);
            }
            if (image.getVertexShaderCode() == null | image.getFragmentShaderCode() == null
                    | image.getAttributes() == null) {
                throw new RuntimeException("Shader deve ser configurado. " +
                        "Utilize o método setShader da class GLImage!");
            }
        }

        GLSurfaceView screen = new GLSurfaceView(this);
        screen.setEGLContextClientVersion(2);
        screen.setRenderer(new Renderer(images, bufferSize));
        screen.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(screen);
    }
}
