package com.example.mori.renderer;

import java.util.ArrayList;

/**
 * Abstração de imagem de ponto.
 * Demostração de atribuir dados à uniform.
 * Created by mori on 09/07/16.
 */
public class DotImage extends GLImage {
    private ArrayList<GLData> datas;
    private ArrayList<float[]> arrays;

    public DotImage(){

        datas = new ArrayList<>();
        arrays = new ArrayList<>();
        ArrayList<GLAttribute> attributes = new ArrayList<>();
        ArrayList<GLUniform> uniforms = new ArrayList<>();

        arrays.add(new float[]{0.0f, 0.5f});
        attributes.add(new GLAttribute("vPosition", false, 0, 0));
        uniforms.add(new GLUniform("color", 1, new float[]{0.5f, 0.0f, 0.5f, 0.1f}, 0));
        addData(datas,
                0,
                attributes,
                uniforms,
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
                GL.GL_POINTS, 0, 1);
    }

    @Override
    public ArrayList<GLData> getDatas() {
        return datas;
    }

    @Override
    public ArrayList<float[]> getArrays() {
        return arrays;
    }
}
