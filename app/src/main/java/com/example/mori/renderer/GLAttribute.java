package com.example.mori.renderer;

import java.nio.Buffer;

/**
 * Abstração de attribute.
 * Created by mori on 09/07/16.
 */
public class GLAttribute {
    private String name = "";
    private boolean normalized = false;
    private int stride = -1;
    private int offset = -1;
    private Buffer array = null;

    /**
     * Construtor
     * @param name nome do atribute que receberá o array
     * @param normalized Se true então o array será normalizado.
     * @param stride quantidade de elementos no array a ser usado pelo attribute.
     * @param offset quantidade de elementos no array que não serão processados.
     */
    public GLAttribute(String name, boolean normalized, int stride, int offset) {
        this.name = name;
        this.normalized = normalized;
        this.stride = stride;
        this.offset = offset;
    }

    /**
     * Construtor
     * @param name nome do atribute que receberá o array
     * @param normalized Se true então o array será normalizado.
     * @param stride quantidade de elementos no array a ser usado pelo attribute.
     * @param array array sem buffer objects
     */
    public GLAttribute(String name, boolean normalized, int stride, float[] array) {
        this.name = name;
        this.normalized = normalized;
        this.stride = stride;
        this.array = GLBuffers.adapt(array);
    }

    public String getName() {
        return name;
    }

    public boolean getNormalized() {
        return normalized;
    }

    public int getStride() {
        return stride;
    }

    public int getOffset() {
        return offset;
    }

    public Buffer getArray() {
        return array;
    }

    public void setNormalized(boolean normalized) {
        this.normalized = normalized;
    }

    public void setStride(int stride) {
        this.stride = stride;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setArray(float[] array) {
        this.array = GLBuffers.adapt(array);
    }
}
