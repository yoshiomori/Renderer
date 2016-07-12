package com.example.mori.renderer;

/**
 * Abstração de uniform.
 * Created by mori on 09/07/16.
 */
public class GLUniform {
    private String name;
    private float[] array;

    public GLUniform(String name, float[] array) {

        this.name = name;
        this.array = array;
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
}
