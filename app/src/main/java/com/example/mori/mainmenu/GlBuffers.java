package com.example.mori.mainmenu;

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
            if (arrays.get(index).floatArray != null) {
                FloatBuffer buffer = adaptFloatArray(arrays.get(index).floatArray);
                GL.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers[index]);
                GL.glBufferData(GL.GL_ARRAY_BUFFER, buffer.limit(), buffer, GL.GL_STATIC_DRAW);
            } else if (arrays.get(index).shortArray != null) {
                ShortBuffer buffer = adaptShortArray(arrays.get(index).shortArray);
                GL.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers[index]);
                GL.glBufferData(GL.GL_ARRAY_BUFFER, buffer.limit(), buffer, GL.GL_STATIC_DRAW);
            }
        }
    }

    private ShortBuffer adaptShortArray(short[] shortArray) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(shortArray.length * Float.SIZE);
        byteBuffer.order(ByteOrder.nativeOrder());
        ShortBuffer floatBuffer = byteBuffer.asShortBuffer();
        floatBuffer.put(shortArray);
        floatBuffer.position(0);
        return floatBuffer;
    }

    private FloatBuffer adaptFloatArray(float[] floatArray) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(floatArray.length * Float.SIZE);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(floatArray);
        floatBuffer.position(0);
        return floatBuffer;
    }

    public void bindArrayBuffer(int index) {
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers[index]);
    }
}
