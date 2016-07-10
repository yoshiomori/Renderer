package com.example.mori.renderer;

import java.util.ArrayList;

/**
 * Abstração de imagem de retângulo
 * Created by mori on 09/07/16.
 */
public class RetangleImage extends GLImage {

    private ArrayList<GLData> datas;
    private ArrayList<float[]> arrays;

    public RetangleImage(){
        datas = new ArrayList<>();
        arrays = new ArrayList<>();
        ArrayList<GLAttribute> attributes = new ArrayList<>();

        arrays.add(new float[]{-0.5f, 0.0f, 0.5f, 0.5f});
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
    public ArrayList<float[]> getArrays() {
        return arrays;
    }
}
