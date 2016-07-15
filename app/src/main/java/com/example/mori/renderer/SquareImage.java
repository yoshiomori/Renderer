package com.example.mori.renderer;

import android.content.Context;

/**
 * Demostração de como desenhar com element array.
 * Created by mori on 10/07/16.
 */
public class SquareImage extends GLImage {
    public SquareImage(Context applicationContext){
        super(applicationContext);
        setShader(
                "/*vertexShader*/" +
                        "attribute vec4 position;" +
                        "void main(){" +
                        "gl_Position = position;" +
                        "}",
                "/* fragmentShader */" +
                        "precision mediump float;" +
                        "void main(){" +
                        "   gl_FragColor = vec4(0.0, 1.0, 0.5, 1.0);" +
                        "}",
                GL.GL_TRIANGLES, 6,
                new short[]{
                        0, 1, 2,
                        2, 1, 3
                }
        );
        setAttribute("position", false, 0,
                -1.0f, 1.0f, 0.0f, 1.0f,
                -0.7f, 1.0f, 0.0f, 1.0f,
                -1.0f, 0.7f, 0.0f, 1.0f,
                -0.7f, 0.7f, 0.0f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(int width, int height) {

    }
}
