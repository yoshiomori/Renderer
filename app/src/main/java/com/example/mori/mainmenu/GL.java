package com.example.mori.mainmenu;

import android.opengl.GLES20;
import android.util.Log;

import java.nio.Buffer;

/**
 * GLES20 com check error
 * Created by mori on 06/07/16.
 */
public class GL{
    public static int glCreateProgram() {
        int program = GLES20.glCreateProgram();
        checkGlError("glCreateProgram");
        return program;
    }

    public static int glCreateShader(int type) {
        int shader = GLES20.glCreateShader(type);
        checkGlError("glCreateShader");
        return shader;
    }

    public static void glShaderSource(int shader, String string) {
        GLES20.glShaderSource(shader, string);
        checkGlError("glShaderSource");
    }

    public static void glCompileShader(int shader) {
        GLES20.glCompileShader(shader);
        checkGlError("glCompileShader");
    }

    public static void glAttachShader(int program, int shader) {
        GLES20.glAttachShader(program, shader);
        checkGlError("glAttachShader");
    }

    public static void glLinkProgram(int program) {
        GLES20.glLinkProgram(program);
        checkGlError("glLinkProgram");
    }

    public static void glGenBuffers(int n, int[] buffers, int offset) {
        GLES20.glGenBuffers(n, buffers, offset);
        checkGlError("glGenBuffers");
    }

    public static void glBindBuffer(int target, int buffer) {
        GLES20.glBindBuffer(target, buffer);
        checkGlError("glBindBuffer");
    }

    public static void glBufferData(int target, int size, Buffer data, int usage) {
        GLES20.glBufferData(target, size, data, usage);
        checkGlError("glBufferData");
    }

    public static void glClearColor(float red, float green, float blue, float alpha) {
        GLES20.glClearColor(red, green, blue, alpha);
        checkGlError("glClearColor");
    }

    public static void glUseProgram(int program) {
        GLES20.glUseProgram(program);
        checkGlError("glUseProgram");
    }

    public static int glGetAttribLocation(int program, String name) {
        int attribLocation = GLES20.glGetAttribLocation(program, name);
        checkGlError("glGetAttribLocation");
        return attribLocation;
    }

    public static void glEnableVertexAttribArray(int index) {
        GLES20.glEnableVertexAttribArray(index);
        checkGlError("glEnableVertexAttribArray");
    }

    public static void glVertexAttribPointer(int indx, int size, int type, boolean normalized,
                                             int stride, int offset) {
        GLES20.glVertexAttribPointer(indx, size, type, normalized, stride, offset);
        checkGlError("glVertexAttribPointer");
    }

    public static void glDrawArrays(int mode, int first, int count) {
        GLES20.glDrawArrays(mode, first, count);
        checkGlError("glDrawArrays");
    }

    private static void checkGlError(String glOperation) {
        int error = GLES20.glGetError();
        if (error != GLES20.GL_NO_ERROR) {
            Log.e("Gl", glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }
}
