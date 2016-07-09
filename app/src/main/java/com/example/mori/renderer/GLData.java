package com.example.mori.renderer;

import java.util.ArrayList;

/**
 * Abstração de dados usados para renderizar com a biblioteca gráfica.
 * Created by mori on 09/07/16.
 */
public class GLData {

    private final int arrayIndex;
    private final ArrayList<GLAttribute> attributes;
    private final ArrayList<GLUniform> uniforms;
    private final String fragmentShaderCode;
    private final String vertexShaderCode;
    private final int mode;
    private final int first;
    private final int count;

    public GLData(int arrayIndex, ArrayList<GLAttribute> attributes, String vertexShaderCode,
                  String fragmentShaderCode, int mode, int first, int count) {

        this.arrayIndex = arrayIndex;
        this.attributes = attributes;
        uniforms = new ArrayList<>();
        this.fragmentShaderCode = fragmentShaderCode;
        this.vertexShaderCode = vertexShaderCode;
        this.mode = mode;
        this.first = first;
        this.count = count;
    }

    public GLData(int arrayIndex, ArrayList<GLAttribute> attributes, ArrayList<GLUniform> uniforms,
                  String vertexShaderCode, String fragmentShaderCode, int mode, int first,
                  int count) {

        this.arrayIndex = arrayIndex;
        this.attributes = attributes;
        this.uniforms = uniforms;
        this.vertexShaderCode = vertexShaderCode;
        this.fragmentShaderCode = fragmentShaderCode;
        this.mode = mode;
        this.first = first;
        this.count = count;
    }

    public String getFragmentShaderCode() {
        return fragmentShaderCode;
    }

    public String getVertexShaderCode() {
        return vertexShaderCode;
    }

    public ArrayList<GLAttribute> getAttributes() {
        return attributes;
    }

    public int getMode() {
        return mode;
    }

    public int getFirst() {
        return first;
    }

    public int getCount() {
        return count;
    }

    public int getIndex() {
        return arrayIndex;
    }

    public ArrayList<GLUniform> getUniforms() {
        return uniforms;
    }
}
