package com.example.mori.mainmenu;

import android.opengl.GLES20;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstração de Programa da biblioteca gráfica.
 * Created by mori on 09/07/16.
 */
public class GLProgram {
    private final int mode;
    private final int first;
    private final int count;
    private int program;
    private int[] programInfo = new int[6];
    public int GL_ACTIVE_ATTRIBUTES = 0;
    public int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 1;
    public int GL_ACTIVE_UNIFORMS = 2;
    public int GL_ACTIVE_UNIFORM_MAX_LENGTH = 3;
    public final int GL_LINK_STATUS = 4;
    public final int GL_ATTACHED_SHADERS = 5;
    private HashMap<String, Integer> attributeLocation;
    private int[] attributeType;
    private final int index;
    private ArrayList<GLAttribute> attributes;

    public GLProgram(int index, ArrayList<GLAttribute> attributes, String vertexShaderCode,
                     String fragmentShaderCode, int mode, int first, int count) {
        this.index = index;
        this.attributes = attributes;
        this.mode = mode;
        this.first = first;
        this.count = count;

        initProgram(vertexShaderCode, fragmentShaderCode);
        initProgramInfo();
        initAttributeLocation();

        if (programInfo[GL_LINK_STATUS] == GLES20.GL_FALSE) {
            throw new RuntimeException("Link error: attached shader: " + programInfo[GL_ATTACHED_SHADERS]);
        }
    }

    private void initAttributeLocation() {
        byte[] name = new byte[programInfo[GL_ACTIVE_ATTRIBUTE_MAX_LENGTH]];
        int[] length = new int[1];
        int[] size = new int[1];
        attributeType = new int[programInfo[GL_ACTIVE_ATTRIBUTES]];
        attributeLocation = new HashMap<>(programInfo[GL_ACTIVE_ATTRIBUTE_MAX_LENGTH]);
        for (int index = 0; index < programInfo[GL_ACTIVE_ATTRIBUTES]; index++) {
            GL.glGetActiveAttrib(program, index, name.length, length, 0, size, 0, attributeType, index, name, 0);
            attributeLocation.put(new String(name, 0, length[0]), index);
        }
    }

    private void initProgramInfo() {
        GL.glGetProgramiv(program, GL.GL_ACTIVE_ATTRIBUTES, programInfo, GL_ACTIVE_ATTRIBUTES);
        GL.glGetProgramiv(program, GLES20.GL_ACTIVE_ATTRIBUTE_MAX_LENGTH,
                programInfo, GL_ACTIVE_ATTRIBUTE_MAX_LENGTH);
        GL.glGetProgramiv(program, GLES20.GL_ACTIVE_UNIFORMS, programInfo, GL_ACTIVE_UNIFORMS);
        GL.glGetProgramiv(program, GLES20.GL_ACTIVE_UNIFORM_MAX_LENGTH, programInfo,
                GL_ACTIVE_UNIFORM_MAX_LENGTH);
        GL.glGetProgramiv(program, GLES20.GL_LINK_STATUS, programInfo,
                GL_LINK_STATUS);
        GL.glGetProgramiv(program, GLES20.GL_ATTACHED_SHADERS, programInfo,
                GL_ATTACHED_SHADERS);
    }

    private void initProgram(String vertexShader, String fragmentShader) {
        program = GL.glCreateProgram();
        if (program == 0)
            throw new RuntimeException("Falha ao criar o programa");
        GL.glAttachShader(program, loadShader(GL.GL_VERTEX_SHADER, vertexShader));
        GL.glAttachShader(program, loadShader(GL.GL_FRAGMENT_SHADER, fragmentShader));
        GL.glLinkProgram(program);
    }

    private int loadShader(int type, String shaderCode){
        int shader = GL.glCreateShader(type);
        GL.glShaderSource(shader, shaderCode);
        GL.glCompileShader(shader);
        return shader;
    }

    public void define(String attributeName, boolean normalized, int stride, int offset) {
        int indx = attributeLocation.get(attributeName);

        /* -1 é não implementado */
        int size = attributeType[indx] == GLES20.GL_FLOAT_VEC4 ? 4
                : attributeType[indx] == GLES20.GL_FLOAT_VEC3 ? 3
                : attributeType[indx] == GLES20.GL_FLOAT_VEC2 ? 2
                : -1;
        int type = attributeType[indx] == GLES20.GL_FLOAT_VEC4
                | attributeType[indx] == GLES20.GL_FLOAT_VEC3
                | attributeType[indx] == GLES20.GL_FLOAT_VEC2 ? GL.GL_FLOAT
                : -1;

        if(size == -1 | type == -1)
            throw new RuntimeException("Tipo do attribute no vertex shader não implementado.");

        GL.glVertexAttribPointer(indx, size, type, normalized, stride * size, offset * size);
        GL.glEnableVertexAttribArray(attributeLocation.get(attributeName));
    }

    public ArrayList<GLAttribute> getAttributes() {
        return attributes;
    }

    public void use() {
        GL.glUseProgram(program);
    }

    public int getMode() {
        return mode;
    }

    public int getFirst() {
        return first;
    }

    public int getCount() {
        return count;
    }

    public int getIndex() {
        return index;
    }
}
