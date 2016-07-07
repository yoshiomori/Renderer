package com.example.mori.mainmenu;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Menu principal do app.
 * Created by mori on 06/07/16.
 */
public class MainMenu implements GLSurfaceView.Renderer{


    private GLProgramObject programObject;
    private GlBufferObjects bufferObjects;
    private GLProgramObject programObject1;
    private GLProgramObject programObject2;
    private GLProgramObject programObject3;
    private GLProgramObject programObject4;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        loadVertices();
        loadShaderProgram();
    }

    private void loadShaderProgram() {
        programObject = new GLProgramObject(
                "/* Vertex Shader */" +
                        "attribute vec2 vPosition;" +
                        "void main() {" +
                        "  gl_Position = vec4(vPosition, 0, 1);" +
                        "  gl_PointSize = 50;" +
                        "}",
                "/* Fragment Shader */" +
                        "precision mediump float;" +
                        "void main() {" +
                        "  gl_FragColor = vec4(1.0, 1.0, 1.0, 1.0);" +
                        "}");

        programObject1 = new GLProgramObject(
                "/* Vertex Shader */" +
                        "attribute vec4 vPosition;" +
                        "void main() {" +
                        "  gl_Position = vPosition;" +
                        "  gl_PointSize = 50;" +
                        "}",
                "/* Fragment Shader */" +
                        "precision mediump float;" +
                        "void main() {" +
                        "  gl_FragColor = vec4(1.0, 1.0, 0.0, 1.0);" +
                        "}");

        programObject2 = new GLProgramObject(
                "/* Vertex Shader */" +
                        "attribute vec2 vPosition;" +
                        "void main() {" +
                        "  gl_Position = vec4(vPosition, 0.0, 1.0);" +
                        "}",
                "/* Fragment Shader */" +
                        "precision mediump float;" +
                        "void main() {" +
                        "  gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);" +
                        "}");

        programObject3 = new GLProgramObject(
                "/* Vertex Shader */" +
                        "attribute vec2 vPosition;" +
                        "void main() {" +
                        "  gl_Position = vec4(vPosition, 0.0, 1.0);" +
                        "}",
                "/* Fragment Shader */" +
                        "precision mediump float;" +
                        "void main() {" +
                        "  gl_FragColor = vec4(0.0, 1.0, 0.0, 1.0);" +
                        "}");

        programObject4 = new GLProgramObject(
                "/* Vertex Shader */" +
                        "attribute vec2 vPosition;" +
                        "void main() {" +
                        "  gl_Position = vec4(vPosition, 0.0, 1.0);" +
                        "}",
                "/* Fragment Shader */" +
                        "precision mediump float;" +
                        "uniform vec4 vColor;" +
                        "void main() {" +
                        "  gl_FragColor = vColor;" +
                        "}");
    }

    private void loadVertices() {
        bufferObjects = new GlBufferObjects(4);
        bufferObjects.set(0, new float[]{0.0f, 0.0f});
        bufferObjects.set(1, new float[]{0.5f, 0.5f, 0.0f, 1.0f});
        bufferObjects.set(2, new float[]{
                -1.0f, 0.0f,
                0.0f, 0.0f,
                -1.0f, -1.0f,
                0.0f, -1.0f});
        bufferObjects.set(3, new short[]{
                0, 3, 2,
                1, 0, 3});
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GL.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // Desenhando com buffer array
        GL.glUseProgram(programObject.get());
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, bufferObjects.get(0));
        GL.glVertexAttribPointer(programObject.getAttributeLocation("vPosition"), 2, GL.GL_FLOAT,
                false, 0, 0);
        GL.glEnableVertexAttribArray(programObject.getAttributeLocation("vPosition"));
        GL.glDrawArrays(GL.GL_POINTS, 0, 1);
        GL.glDisableVertexAttribArray(programObject.getAttributeLocation("vPosition"));
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);

        // Desenhando com buffer array e outro programa objeto
        GL.glUseProgram(programObject1.get());
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, bufferObjects.get(1));
        GL.glVertexAttribPointer(programObject1.getAttributeLocation("vPosition"), 4, GL.GL_FLOAT,
                false, 0, 0);
        GL.glEnableVertexAttribArray(programObject1.getAttributeLocation("vPosition"));
        GL.glDrawArrays(GL.GL_POINTS, 0, 1);
        GL.glDisableVertexAttribArray(programObject1.getAttributeLocation("vPosition"));
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);

        // Desenhando sem buffer array, com elementos de vértice e outro programa objeto
        GL.glUseProgram(programObject2.get());
        GL.glVertexAttribPointer(programObject2.getAttributeLocation("vPosition"), 2, GL.GL_FLOAT,
                false, 0, GlBufferObjects.adaptData(new float[]{
                        -0.5f, 0.5f,
                        0.5f, 0.5f,
                        -0.5f, -0.5f,
                        0.5f, -0.5f}));
        GL.glEnableVertexAttribArray(programObject2.getAttributeLocation("vPosition"));
        GL.glDrawElements(GL.GL_TRIANGLES, 6, GL.GL_UNSIGNED_SHORT,
                GlBufferObjects.adaptData(new short[]{
                        0, 3, 2,
                        1, 0, 3}));

        // Desenhando com buffer array, com elementos de vértices e no mesmo programa objeto
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, bufferObjects.get(2));
        GL.glVertexAttribPointer(programObject2.getAttributeLocation("vPosition"), 2, GL.GL_FLOAT,
                false, 0, 0);
        GL.glEnableVertexAttribArray(programObject2.getAttributeLocation("vPosition"));
        GL.glDrawElements(GL.GL_TRIANGLES, 6, GL.GL_UNSIGNED_SHORT,
                GlBufferObjects.adaptData(new short[]{
                        0, 3, 2,
                        1, 0, 3}));
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);

        // Desenhando com buffer array, element array buffer e em outro programa objeto
        GL.glUseProgram(programObject3.get());
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, bufferObjects.get(2));
        GL.glVertexAttribPointer(programObject3.getAttributeLocation("vPosition"), 2, GL.GL_FLOAT,
                false, 0, 0);
        GL.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, bufferObjects.get(3));
        GL.glEnableVertexAttribArray(programObject3.getAttributeLocation("vPosition"));
        GL.glDrawElements(GL.GL_TRIANGLES, 6, GL.GL_UNSIGNED_SHORT, 0);
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);
        GL.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, 0);

        // Desenhando com buffer array, element array buffer, uniform e em outro programa
        GL.glUseProgram(programObject4.get());
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, bufferObjects.get(2));
        GL.glVertexAttribPointer(programObject4.getAttributeLocation("vPosition"), 2, GL.GL_FLOAT,
                false, 0, 0);
        GL.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, bufferObjects.get(3));
        GL.glEnableVertexAttribArray(programObject4.getAttributeLocation("vPosition"));
        GL.glUniform4fv(programObject4.getUniformLocation("vColor"), 1, new float[]{0.0f, 0.0f, 1.0f, 1.0f}, 0);
        GL.glDrawElements(GL.GL_TRIANGLES, 6, GL.GL_UNSIGNED_SHORT, 0);
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);
        GL.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, 0);
    }
}
