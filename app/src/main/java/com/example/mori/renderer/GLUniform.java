package com.example.mori.renderer;

/**
 * Abstração de uniform.
 * Created by mori on 09/07/16.
 */
public class GLUniform {
    private int xi = 0;
    private String name = "";
    private float[] array = null;
    private float xf;

    public GLUniform(String name, float[] array) {
        this.name = name;
        this.array = array;
    }

    public GLUniform(String name, int x) {
        this.name = name;
        this.xi = x;
    }

    public GLUniform(String name, float x) {
        this.name = name;
        xf = x;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float[] getArray() {
        return array;
    }

    public void setArray(float[] array) {
        this.array = array;
    }

    public int getXi() {
        return xi;
    }

    public void setXi(int x) {
        xi = x;
    }

    public void setXf(float x) {
        xf = x;
    }

    public float getXf() {
        return xf;
    }
}
