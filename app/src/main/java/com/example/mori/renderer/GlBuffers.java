package com.example.mori.renderer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

/**
 * Abstração de objeto buffer
 * Created by mori on 07/07/16.
 */
public class GlBuffers {
    private int[] buffers;

    public GlBuffers(ArrayList<GLArray> arrays) {
        buffers = new int[arrays.size()];
        GL.glGenBuffers(arrays.size(), buffers, 0);
        for (int index = 0; index < arrays.size(); index++) {
            Buffer buffer = arrays.get(index).getArray();
            GL.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers[index]);
            GL.glBufferData(GL.GL_ARRAY_BUFFER, buffer.limit(), buffer, GL.GL_STATIC_DRAW);
        }
    }

    public void bindArrayBuffer(int arrayIndex) {
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, arrayIndex >= 0 ? buffers[arrayIndex] : 0);
    }
}
