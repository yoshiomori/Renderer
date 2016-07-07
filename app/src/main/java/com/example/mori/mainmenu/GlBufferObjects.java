package com.example.mori.mainmenu;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Abstração de objeto buffer
 * Created by mori on 07/07/16.
 */
public class GlBufferObjects {
    private int[] buffers;
    private int[] sizes;

    public GlBufferObjects(int n) {
        buffers = new int[n];
        sizes = new int[n];
        GL.glGenBuffers(n, buffers, 0);
    }

    public void set(int index, float[] data) {
        FloatBuffer buffer = adaptData(data);
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers[index]);
        GL.glBufferData(GL.GL_ARRAY_BUFFER, buffer.limit(), buffer, GL.GL_STATIC_DRAW);
        sizes[index] = Float.SIZE;
    }

    private FloatBuffer adaptData(float[] array) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(array.length * Float.SIZE);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(array);
        floatBuffer.position(0);
        return floatBuffer;
    }

    public int get(int index) {
        return buffers[index];
    }
}
