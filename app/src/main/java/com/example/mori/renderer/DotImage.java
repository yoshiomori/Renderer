package com.example.mori.renderer;

/**
 * Abstração de imagem de ponto.
 * Demostração de atribuir dados à uniform.
 * Created by mori on 09/07/16.
 */
public class DotImage extends GLImage {
    public DotImage(){
        setArray(new float[]{0.0f, 0.5f});
        setUniforms(new GLUniform("color", 1, new float[]{0.5f, 0.0f, 0.5f, 0.1f}, 0));
        setShader(
                "/* Vertex Shader */" +
                        "attribute vec2 vPosition;" +
                        "void main() {" +
                        "  gl_Position = vec4(vPosition, 0, 1);" +
                        "  gl_PointSize = 50;" +
                        "}",
                "/* Fragment Shader */" +
                        "precision mediump float;" +
                        "uniform vec4 color;" +
                        "void main() {" +
                        "  gl_FragColor = color;" +
                        "}",
                new GLAttribute("vPosition", false, 0, 0));
        setDraw(GL.GL_POINTS, 0, 1);
    }
}
