package com.example.mori.mainmenu;

import android.opengl.GLES20;

import java.util.HashMap;

/**
 * Abstração de programa objeto da biblioteca gráfica
 * Created by mori on 07/07/16.
 */
public class GLProgramObject extends ErrorCondition {
    private int program;
    private int[] programInfo = new int[4];
    public int GL_ACTIVE_ATTRIBUTES = 0;
    public int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 1;
    public int GL_ACTIVE_UNIFORMS = 2;
    public int GL_ACTIVE_UNIFORM_MAX_LENGTH = 3;
    private HashMap<String, Integer> attributeLocation;

    public GLProgramObject(String vertexShader, String fragmentShader) {
        initProgram(vertexShader, fragmentShader);
        initProgramInfo();
        initAttributeLocation();
    }

    private void initAttributeLocation() {
        byte[] name = new byte[programInfo[GL_ACTIVE_ATTRIBUTE_MAX_LENGTH]];
        int[] length = new int[1];
        int[] size = new int[1];
        int[] type = new int[1];
        attributeLocation = new HashMap<>(programInfo[GL_ACTIVE_ATTRIBUTE_MAX_LENGTH]);
        for (int index = 0; index < programInfo[GL_ACTIVE_ATTRIBUTES]; index++) {
            GL.glGetActiveAttrib(program, index, name.length, length, 0, size, 0, type, 0, name, 0);
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
    }

    private void initProgram(String vertexShader, String fragmentShader) {
        program = GL.glCreateProgram();
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

    public int get() {
        return program;
    }

    public int getAttributeLocation(String attributeName) {
        return attributeLocation.get(attributeName);
    }
}
