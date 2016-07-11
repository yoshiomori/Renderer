package com.example.mori.renderer;

import java.nio.Buffer;
import java.util.ArrayList;

/**
 * Abstração de dados usados para renderizar com a biblioteca gráfica.
 * Created by mori on 09/07/16.
 */
public class GLData {

    private int arrayIndex = -1;
    private final ArrayList<GLAttribute> attributes;
    private final ArrayList<GLUniform> uniforms;
    private final String fragmentShaderCode;
    private final String vertexShaderCode;
    private final int mode;
    private final int first;
    private final GLArray indices;
    private final int count;

    public GLData(ArrayList<GLAttribute> attributes, String vertexShaderCode,
                  String fragmentShaderCode, int mode, int first, int count) {

        this.attributes = attributes;
        uniforms = new ArrayList<>();
        this.fragmentShaderCode = fragmentShaderCode;
        this.vertexShaderCode = vertexShaderCode;
        this.mode = mode;
        this.first = first;
        this.count = count;
        indices = null;
    }

    public GLData(ArrayList<GLAttribute> attributes, ArrayList<GLUniform> uniforms,
                  String vertexShaderCode, String fragmentShaderCode, int mode, int first,
                  int count) {

        this.attributes = attributes;
        this.uniforms = uniforms;
        this.vertexShaderCode = vertexShaderCode;
        this.fragmentShaderCode = fragmentShaderCode;
        this.mode = mode;
        this.first = first;
        this.count = count;
        indices = null;
    }

    public GLData(ArrayList<GLAttribute> attributes, String vertexShaderCode,
                  String fragmentShaderCode, int mode, int count, short[] indices) {

        this.attributes = attributes;
        uniforms = new ArrayList<>();
        this.vertexShaderCode = vertexShaderCode;
        this.fragmentShaderCode = fragmentShaderCode;
        this.mode = mode;
        first = -1;
        this.indices = new GLArray(indices);
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

    public int getArrayIndex() {
        return arrayIndex;
    }

    public ArrayList<GLUniform> getUniforms() {
        return uniforms;
    }

    public void setArrayIndex(int arrayIndex){
        this.arrayIndex = arrayIndex;
    }

    public Buffer getIndices() {
        return indices != null ? indices.getArray() : null;
    }
}
