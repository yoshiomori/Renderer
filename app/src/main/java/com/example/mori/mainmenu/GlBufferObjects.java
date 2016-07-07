package com.example.mori.mainmenu;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

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

    public void set(int index, short[] data) {
        ShortBuffer buffer = adaptData(data);
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers[index]);
        GL.glBufferData(GL.GL_ARRAY_BUFFER, buffer.limit(), buffer, GL.GL_STATIC_DRAW);
        sizes[index] = Short.SIZE;
    }

    public static FloatBuffer adaptData(float[] data) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(data.length * Float.SIZE);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(data);
        floatBuffer.position(0);
        return floatBuffer;
    }

    public int get(int index) {
        return buffers[index];
    }

    public static ShortBuffer adaptData(short[] data) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(data.length * Short.SIZE);
        byteBuffer.order(ByteOrder.nativeOrder());
        ShortBuffer shortBuffer = byteBuffer.asShortBuffer();
        shortBuffer.put(data);
        shortBuffer.position(0);
        return shortBuffer;
    }
}
