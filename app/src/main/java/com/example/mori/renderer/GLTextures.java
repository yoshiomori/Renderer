package com.example.mori.renderer;

import android.graphics.Bitmap;
import android.opengl.GLES20;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Abstração de unidade de textura.
 * Created by mori on 12/07/16.
 */
public class GLTextures {
    int[] textures;
    public GLTextures(GLImage[] images, int texturesSize) {
        textures = new int[texturesSize];
        GL.glGenTextures(texturesSize, textures, 0);
        for (GLImage image :
                images) {
            Bitmap data = image.getBitmap();
            if (data != null) {
                GL.glBindTexture(GLES20.GL_TEXTURE_2D, textures[image.getTextureIndex()]);
                GL.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, data.getWidth(),
                        data.getHeight(), 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, adapt(data));
                GL.glTexParameteri(
                        GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
                GL.glTexParameteri(
                        GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
                GL.glTexParameteri(
                        GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
                GL.glTexParameteri(
                        GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
                GL.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
            }
        }
    }

    public static Buffer adapt(Bitmap data) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[data.getByteCount()]);
        data.copyPixelsToBuffer(byteBuffer);
        byteBuffer.position(0);
        return byteBuffer;
    }

    public void bindTextures(int textureIndex) {
            GL.glActiveTexture(GLES20.GL_TEXTURE0);
            GL.glBindTexture(GLES20.GL_TEXTURE_2D,
                    textureIndex < 0 ? 0 : this.textures[textureIndex]);
    }
}
