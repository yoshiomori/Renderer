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
    }

    private void loadVertices() {
        bufferObjects = new GlBufferObjects(2);
        bufferObjects.set(0, new float[]{0.0f, 0.0f});
        bufferObjects.set(1, new float[]{0.5f, 0.5f, 0.0f, 1.0f});
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GL.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        GL.glUseProgram(programObject.get());
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, bufferObjects.get(0));
        GL.glVertexAttribPointer(programObject.getAttributeLocation("vPosition"), 2, GL.GL_FLOAT, false, 0, 0);
        GL.glEnableVertexAttribArray(programObject.getAttributeLocation("vPosition"));
        GL.glDrawArrays(GL.GL_POINTS, 0, 1);

        GL.glUseProgram(programObject1.get());
        GL.glBindBuffer(GL.GL_ARRAY_BUFFER, bufferObjects.get(1));
        GL.glVertexAttribPointer(programObject1.getAttributeLocation("vPosition"), 4, GL.GL_FLOAT, false, 0, 0);
        GL.glEnableVertexAttribArray(programObject1.getAttributeLocation("vPosition"));
        GL.glDrawArrays(GL.GL_POINTS, 0, 1);
    }
}
