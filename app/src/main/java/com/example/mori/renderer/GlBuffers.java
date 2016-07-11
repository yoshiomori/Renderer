package com.example.mori.renderer;

import android.opengl.GLES20;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Abstração de objeto buffer
 * Created by mori on 07/07/16.
 */
public class GlBuffers {
    private int[] buffers;

    public GlBuffers(GLImage[] arrays, int bufferSize) {
        buffers = new int[bufferSize];
        GL.glGenBuffers(bufferSize, buffers, 0);
        for (GLImage image :
                arrays) {
            if (image.getArray() != null) {
                loadArray(adapt(image.getArray()), image.getArrayIndex());
            }
            if (image.getElementArray() != null) {
                loadArray(adapt(image.getElementArray()), image.getElementArrayIndex());
            }
        }
    }

    private void loadArray(Buffer buffer, int indice) {
        GL.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[indice]);
        GL.glBufferData(GLES20.GL_ARRAY_BUFFER, buffer.limit(), buffer, GLES20.GL_STATIC_DRAW);
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

    public void bindArrayBuffer(int arrayIndex) {
        GL.glBindBuffer(GLES20.GL_ARRAY_BUFFER, arrayIndex >= 0 ? buffers[arrayIndex] : 0);
    }
}
