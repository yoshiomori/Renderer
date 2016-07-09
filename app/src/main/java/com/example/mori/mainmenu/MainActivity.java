package com.example.mori.mainmenu;


import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ArrayList<float[]> arrayList = new ArrayList<>();
        arrayList.add(new float[]{0.0f, 0.0f});  /* index 0 */
        arrayList.add(new float[]{0.5f, 0.5f, 0.0f, 0.5f});  /* index 1 */



        ArrayList<GLData> datas = new ArrayList<>();

        int arrayIndex = 0;
        ArrayList<GLAttribute> attributes = new ArrayList<>();
        attributes.add(new GLAttribute("vPosition", false, 0, 0));
        String vertexShaderCode = "/* Vertex Shader */" +
                "attribute vec2 vPosition;" +
                "void main() {" +
                "  gl_Position = vec4(vPosition, 0, 1);" +
                "  gl_PointSize = 50;" +
                "}";
        String fragmentShaderCode = "/* Fragment Shader */" +
                "precision mediump float;" +
                "void main() {" +
                "  gl_FragColor = vec4(1.0, 1.0, 1.0, 1.0);" +
                "}";
        int mode = GL.GL_POINTS;
        int first = 0;
        int count = 1;

        datas.add(new GLData(arrayIndex, attributes, vertexShaderCode, fragmentShaderCode, mode, first, count));

        int arrayIndex1 = 1;
        ArrayList<GLAttribute> attributes1 = new ArrayList<>();
        attributes1.add(new GLAttribute("vPosition", false, 2, 0));
        String vertexShaderCode1 = "/* Vertex Shader */" +
                "attribute vec2 vPosition;" +
                "void main() {" +
                "  gl_Position = vec4(vPosition, 0, 1);" +
                "  gl_PointSize = 50;" +
                "}";
        String fragmentShaderCode1 = "/* Fragment Shader */" +
                "precision mediump float;" +
                "void main() {" +
                "  gl_FragColor = vec4(1.0, 1.0, 1.0, 1.0);" +
                "}";
        int mode1 = GL.GL_POINTS;
        int first1 = 0;
        int count1 = 2;

        datas.add(new GLData(arrayIndex1, attributes1, vertexShaderCode1, fragmentShaderCode1, mode1, first1, count1));



        GLSurfaceView screen = new GLSurfaceView(this);
        screen.setEGLContextClientVersion(2);
        screen.setRenderer(new MainMenu(arrayList, datas));
        screen.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(screen);
    }
}
