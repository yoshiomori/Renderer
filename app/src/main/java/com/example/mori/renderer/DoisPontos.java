package com.example.mori.renderer;

import java.util.ArrayList;

/**
 * Demostração de como usar o array buffer object
 * Created by mori on 09/07/16.
 */
public class DoisPontos extends GLImage {

    private ArrayList<GLData> datas;
    private ArrayList<GLArray> arrays;

    public DoisPontos(){
        datas = new ArrayList<>();
        arrays = new ArrayList<>();  // Usando o array buffer object
        ArrayList<GLAttribute> attributes = new ArrayList<>();

        arrays.add(new GLArray(new float[]{-0.5f, 0.0f, 0.5f, 0.5f})); // Adicionando elemento no array buffer
        attributes.add(new GLAttribute("vPosition", false, 0, 0));
        addData(datas,
                0,
                attributes,
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
                        "}",
                GL.GL_POINTS, 0, 2);
    }

    @Override
    public ArrayList<GLData> getDatas() {
        return datas;
    }

    @Override
    public ArrayList<GLArray> getArrays() {
        return arrays;
    }
}
