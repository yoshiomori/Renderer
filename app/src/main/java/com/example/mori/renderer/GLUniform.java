package com.example.mori.renderer;

/**
 * Abstração de uniform.
 * Created by mori on 09/07/16.
 */
public class GLUniform {
    private int x = 0;
    private String name = "";
    private float[] array = null;

    public GLUniform(String name, float[] array) {
        this.name = name;
        this.array = array;
    }

    public GLUniform(String name, int x) {
        this.name = name;
        this.x = x;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
