package com.example.mori.renderer;

import java.util.ArrayList;

/**
 * Abstração de uma imagem na biblioteca gráfica.
 * Created by mori on 09/07/16.
 */
public abstract class GLImage {
    protected void addData(ArrayList<GLData> datas, int arrayIndex,
                           ArrayList<GLAttribute> attributes, String vertexShaderCode,
                           String fragmentShaderCode, int mode, int first, int count){

        datas.add(new GLData(arrayIndex, attributes, vertexShaderCode, fragmentShaderCode, mode,
                first, count));
    }

    protected void addData(ArrayList<GLData> datas, int arrayIndex,
                           ArrayList<GLAttribute> attributes, ArrayList<GLUniform> uniforms,
                           String vertexShaderCode, String fragmentShaderCode, int mode, int first,
                           int count) {

        datas.add(new GLData(arrayIndex, attributes, uniforms, vertexShaderCode, fragmentShaderCode,
                mode, first, count));
    }

    protected void addData(ArrayList<GLData> datas, ArrayList<GLAttribute> attributes,
                           String vertexShaderCode, String fragmentShaderCode, int mode, int first,
                           int count) {

        datas.add(new GLData(attributes, vertexShaderCode, fragmentShaderCode, mode, first, count));
    }

    public abstract ArrayList<GLData> getDatas();

    public abstract ArrayList<float[]> getArrays();
}
