package com.example.mori.mainmenu;

import android.opengl.GLES20;

import java.util.HashMap;

/**
 * Abstração de programa objeto da biblioteca gráfica
 * Created by mori on 07/07/16.
 */
public class GLProgramObject extends ErrorCondition {
    private static int GL_ACTIVE_ATTRIBUTES = 0;
    private static int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 1;
    private static int GL_ACTIVE_UNIFORMS = 2;
    private static int GL_ACTIVE_UNIFORM_MAX_LENGTH = 3;
    private static final int GL_LINK_STATUS = 4;
    private int program;
    private int[] programInfo = new int[5];
    private HashMap<String, Integer> attributeLocation;
    private HashMap<String, Integer> uniformLocation;

    public GLProgramObject(String vertexShader, String fragmentShader) {
        initProgram(vertexShader, fragmentShader);
        initProgramInfo();
        initAttributeLocation();
        initUniformLocation();
    }

    private void initUniformLocation() {
        byte[] name = new byte[programInfo[GL_ACTIVE_UNIFORM_MAX_LENGTH]];
        int[] length = new int[1];
        int[] size = new int[1];
        int[] type = new int[1];
        uniformLocation = new HashMap<>(programInfo[GL_ACTIVE_UNIFORMS]);
        for (int index = 0; index < programInfo[GL_ACTIVE_UNIFORMS]; index++) {
            GL.glGetActiveUniform(program, index, name.length, length, 0, size, 0, type, 0, name, 0);
            uniformLocation.put(new String(name, 0, length[0]), index);
        }
    }

    private void initAttributeLocation() {
        byte[] name = new byte[programInfo[GL_ACTIVE_ATTRIBUTE_MAX_LENGTH]];
        int[] length = new int[1];
        int[] size = new int[1];
        int[] type = new int[1];
        attributeLocation = new HashMap<>(programInfo[GL_ACTIVE_ATTRIBUTES]);
        for (int index = 0; index < programInfo[GL_ACTIVE_ATTRIBUTES]; index++) {
            GL.glGetActiveAttrib(program, index, name.length, length, 0, size, 0, type, 0, name, 0);
            attributeLocation.put(new String(name, 0, length[0]), index);
        }
    }

    private void initProgramInfo() {
        GL.glGetProgramiv(program, GL.GL_ACTIVE_ATTRIBUTES, programInfo, GL_ACTIVE_ATTRIBUTES);
        GL.glGetProgramiv(program, GL.GL_ACTIVE_ATTRIBUTE_MAX_LENGTH,
                programInfo, GL_ACTIVE_ATTRIBUTE_MAX_LENGTH);
        GL.glGetProgramiv(program, GL.GL_ACTIVE_UNIFORMS, programInfo, GL_ACTIVE_UNIFORMS);
        GL.glGetProgramiv(program, GL.GL_ACTIVE_UNIFORM_MAX_LENGTH, programInfo,
                GL_ACTIVE_UNIFORM_MAX_LENGTH);
        GL.glGetProgramiv(program, GL.GL_LINK_STATUS, programInfo, GL_LINK_STATUS);
        e(programInfo[GL_LINK_STATUS] == GL.GL_FALSE, this.toString(), "Link fail");
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
        int[] params = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, params, 0);
        e(params[0]==GL.GL_FALSE, "loadShader", "Erro no "
                + (type==GL.GL_VERTEX_SHADER ? "vertex " : "fragment ") + "shader.");
        return shader;
    }

    public int get() {
        return program;
    }

    public int getAttributeLocation(String attributeName) {
        e(!attributeLocation.containsKey(attributeName), "getAttributeLocation", attributeName
                + " attribute não existe!");
        return attributeLocation.get(attributeName);
    }

    public int getUniformLocation(String uniformName) {
        e(!uniformLocation.containsKey(uniformName), "getUniformLocation", uniformName
                + " uniform não existe!");
        return uniformLocation.get(uniformName);
    }
}
