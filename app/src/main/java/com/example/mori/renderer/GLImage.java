package com.example.mori.renderer;

import java.util.ArrayList;

/**
 * Abstração de uma imagem na biblioteca gráfica.
 * Created by mori on 09/07/16.
 */
public abstract class GLImage {
    GLData data;
    ArrayList<GLArray> arrays;
    protected void addData(ArrayList<GLAttribute> attributes, String vertexShaderCode,
                           String fragmentShaderCode, int mode, int first, int count){

        data = new GLData(attributes, vertexShaderCode, fragmentShaderCode, mode, first, count);
    }

    protected void addData(ArrayList<GLAttribute> attributes, ArrayList<GLUniform> uniforms,
                           String vertexShaderCode, String fragmentShaderCode, int mode, int first,
                           int count) {

        data = new GLData(attributes, uniforms, vertexShaderCode, fragmentShaderCode, mode, first,
                count);
    }

    public GLData getData(){
        return data;
    }

    public ArrayList<GLArray> getArrays(){
        return arrays;
    }
}
