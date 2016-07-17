package com.example.mori.renderer;

/**
 * Demostração de desenho sem array e element array.
 * Created by mori on 15/07/16.
 */
public class YellowPointImage extends GLImage {
    public YellowPointImage() {
        setShader(
                "/* Vertex Shader */" +
                        "attribute vec4 position;" +
                        "void main() {" +
                        "   gl_Position = position;" +
                        "   gl_PointSize = 50;" +
                        "}",
                "/* Fragment Shader */" +
                        "precision mediump float;" +
                        "void main() {" +
                        "   gl_FragColor = vec4(1, 1, 0, 1);" +
                        "}",
                GL.GL_POINTS, 0, 1
        );
        setAttribute("position", false, 0, 0f, 0f, 0f, 1f);
    }

    @Override
    public void onSurfaceChanged(int width, int height) {

    }

    @Override
    public void onMove(float dx, float dy, float x, float y) {

    }

    @Override
    public void onDown(float x, float y) {

    }

    @Override
    public void onUp() {

    }
}
