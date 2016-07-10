package com.example.mori.renderer;

import android.opengl.GLES20;

import java.nio.Buffer;

/**
 * GLES20 com check error
 * Created by mori on 06/07/16.
 */
public class GL extends ErrorCondition{
    public static final int GL_ARRAY_BUFFER = GLES20.GL_ARRAY_BUFFER;
    public static final int GL_FLOAT = GLES20.GL_FLOAT;
    public static final int GL_VERTEX_SHADER = GLES20.GL_VERTEX_SHADER;
    public static final int GL_FRAGMENT_SHADER = GLES20.GL_FRAGMENT_SHADER;
    public static final int GL_ACTIVE_ATTRIBUTES = GLES20.GL_ACTIVE_ATTRIBUTES;
    public static final int GL_STATIC_DRAW = GLES20.GL_STATIC_DRAW;
    public static final int GL_POINTS = GLES20.GL_POINTS;
    public static final int GL_TRIANGLES = GLES20.GL_TRIANGLES;

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

    public static void glVertexAttribPointer(int indx, int size, int type, boolean normalized,
                                             int stride, Buffer ptr) {
        GLES20.glVertexAttribPointer(indx, size, type, normalized, stride, ptr);
        checkGlError("glVertexAttribPointer");
    }

    public static void glDrawArrays(int mode, int first, int count) {
        GLES20.glDrawArrays(mode, first, count);
        checkGlError("glDrawArrays");
    }

    private static void checkGlError(String glOperation) {
        int error = GLES20.glGetError();
        e(error != GLES20.GL_NO_ERROR, "Gl", glOperation + ": glError " + error);
    }

    public static void glGetProgramiv(int program, int pname, int[] params, int offset) {
        GLES20.glGetProgramiv(program, pname, params, offset);
        checkGlError("glGetProgramiv");
    }

    public static void glGetActiveUniform(int program, int index, int bufrsize, int[] length,
                                          int lengthOffset, int[] size, int sizeOffset, int[] type,
                                          int typeOffset, byte[] name, int nameOffset) {
        GLES20.glGetActiveUniform(program, index, bufrsize, length, lengthOffset, size,
                sizeOffset, type, typeOffset, name, nameOffset);
        checkGlError("glGetActiveUniform");
    }

    public static void glGetActiveAttrib(int program, int index, int bufsize, int[] length,
                                         int lengthOffset, int[] size, int sizeOffset, int[] type,
                                         int typeOffset, byte[] name, int nameOffset) {
        GLES20.glGetActiveAttrib(program, index, bufsize, length, lengthOffset, size, sizeOffset,
                type, typeOffset, name, nameOffset);
        checkGlError("glGetActiveAttrib");
    }
}
