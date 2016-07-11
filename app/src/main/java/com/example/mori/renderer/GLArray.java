package com.example.mori.renderer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Abstração de um array para a biblioteca gráfica.
 * Created by mori on 09/07/16.
 */
public class GLArray {
    private Buffer array;

    public GLArray(float[] array) {
        this.array = adapt(array);
    }

    public GLArray(short[] indices) {
        array = adapt(indices);
    }

    public static ShortBuffer adapt(short[] shortArray) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(shortArray.length * Float.SIZE);
        byteBuffer.order(ByteOrder.nativeOrder());
        ShortBuffer floatBuffer = byteBuffer.asShortBuffer();
        floatBuffer.put(shortArray);
        floatBuffer.position(0);
        return floatBuffer;
    }

    public static FloatBuffer adapt(float[] floatArray) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(floatArray.length * Float.SIZE);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(floatArray);
        floatBuffer.position(0);
        return floatBuffer;
    }

    public Buffer getArray() {
        return array;
    }
}
