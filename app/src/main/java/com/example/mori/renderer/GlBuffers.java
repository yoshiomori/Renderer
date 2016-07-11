package com.example.mori.renderer;

import android.opengl.GLES20;

import java.nio.Buffer;
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
            GL.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[index]);
            GL.glBufferData(GLES20.GL_ARRAY_BUFFER, buffer.limit(), buffer, GLES20.GL_STATIC_DRAW);
        }
    }

    public void bindArrayBuffer(int arrayIndex) {
        GL.glBindBuffer(GLES20.GL_ARRAY_BUFFER, arrayIndex >= 0 ? buffers[arrayIndex] : 0);
    }
}
