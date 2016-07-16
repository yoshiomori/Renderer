package com.example.mori.renderer;

/**
 * Demostração de uso do element array buffer
 * Created by mori on 11/07/16.
 */
public class SquareBlueImage extends GLImage {
    public SquareBlueImage() {
        setElementArray(new short[]{
                0, 1, 2,
                2, 1, 3
        });
        setArray(1.0f, 1.0f, 0.0f, 1.0f,
                0.7f, 1.0f, 0.0f, 1.0f,
                1.0f, 0.7f, 0.0f, 1.0f,
                0.7f, 0.7f, 0.0f, 1.0f);
        setShader(
                "/*vertexShader*/" +
                        "attribute vec4 position;" +
                        "void main(){" +
                        "gl_Position = position;" +
                        "}",
                "/* fragmentShader */" +
                        "precision mediump float;" +
                        "void main(){" +
                        "   gl_FragColor = vec4(0.2, 0.1, 0.8, 1.0);" +
                        "}",
                GL.GL_TRIANGLES, 0, 6
        );
        setAttribute("position", false, 0, 0);
    }

    @Override
    public void onSurfaceChanged(int width, int height) {

    }
}
