package com.example.mori.mainmenu;

import android.graphics.Bitmap;
import android.opengl.GLES20;

import java.nio.ByteBuffer;

/**
 * Abstração de objeto textura.
 * Created by mori on 08/07/16.
 */
public class GLTextureObjects {
    private final int[] textures;

    public GLTextureObjects(int n) {
        textures = new int[n];
        GLES20.glGenTextures(n, textures, 0);
    }

    public void set(int index, Bitmap data) {
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textures[index]);
        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, data.getWidth(), data.getHeight(), 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, adaptData(data));
        GLES20.glTexParameteri ( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR );
        GLES20.glTexParameteri ( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR );
        GLES20.glTexParameteri ( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE );
        GLES20.glTexParameteri ( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE );
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }

    private static ByteBuffer adaptData(Bitmap data) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[data.getByteCount()]);
        data.copyPixelsToBuffer(byteBuffer);
        byteBuffer.position(0);
        return byteBuffer;
    }

    public int get(int index) {
        return textures[index];
    }
}
