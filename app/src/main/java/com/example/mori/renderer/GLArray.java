package com.example.mori.renderer;

/**
 * Abstração de um array para a biblioteca gráfica.
 * Created by mori on 09/07/16.
 */
public class GLArray {
    public float[] floatArray;
    public short[] shortArray;

    public GLArray(float[] array) {
        floatArray = array;
        shortArray = null;
    }
}
