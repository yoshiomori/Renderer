package com.example.mori.renderer;

/**
 * Definido objetos com posição.
 * Created by mori on 17/07/16.
 */
public class TwoDots extends GLImage{
    public TwoDots() {
        setArray(0.0f, 0.0f);
        setUniform("color", 0.5f, 0.2f, 0.2f, 0.1f);
        setShader(
                "/* Vertex Shader */" +
                        "attribute vec2 vertex;" +
                        "uniform vec2 position;" +
                        "mat4 translate(float x, float y, float z) {" +
                        "   return mat4(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, x, y, z, 1);" +
                        "}" +
                        "void main() {" +
                        "  gl_Position = translate(position.x, position.y, 0) * vec4(vertex, 0, 1);" +
                        "  gl_PointSize = 50;" +
                        "}",
                "/* Fragment Shader */" +
                        "precision mediump float;" +
                        "uniform vec4 color;" +
                        "void main() {" +
                        "  gl_FragColor = color;" +
                        "}",
                GL.GL_POINTS, 0, 1
        );
        setAttribute("vertex", false, 0, 0);
        setPositionName("position");
        addObject(new float[]{0f, 0f});
        addObject(new float[]{0.6f, 0.4f});
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
