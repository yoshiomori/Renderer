package com.example.mori.renderer;

/**
 * Demostração de como usar o array buffer object
 * Created by mori on 09/07/16.
 */
public class DoisPontos extends GLImage {
    public DoisPontos(){
        setArray(new float[]{-0.5f, 0.0f, 0.5f, 0.5f});
        setShader(
                "/* Vertex Shader */" +
                        "attribute vec2 vPosition;" +
                        "void main() {" +
                        "  gl_Position = vec4(vPosition, 0, 1);" +
                        "  gl_PointSize = 50;" +
                        "}",
                "/* Fragment Shader */" +
                        "precision mediump float;" +
                        "void main() {" +
                        "  gl_FragColor = vec4(1.0, 0.0, 0.0, 0.1);" +
                        "}"
        );
        setAttribute("vPosition", false, 0, 0);
        setDraw(GL.GL_POINTS, 0, 2);
    }
}
