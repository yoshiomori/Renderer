package com.example.mori.renderer;

import java.util.ArrayList;

/**
 * Demostração de como desenhar um triângulo
 * Created by mori on 09/07/16.
 */
public class TrianglesImage extends GLImage {
    public TrianglesImage(){
        ArrayList<GLAttribute> attributes = new ArrayList<>();
        attributes.add(new GLAttribute("position", false, 0, new float[]{
                0.0f, 0.5f, 0.0f, 1.0f,
                0.5f, 0.0f, 0.0f, 1.0f,
                0.0f, -0.5f, 0.0f, 1.0f
        }));
        addData(attributes,
                "/*vertexShader*/" +
                        "attribute vec4 position;" +
                        "void main(){" +
                        "gl_Position = position;" +
                        "}",
                "/* fragmentShader */" +
                        "precision mediump float;" +
                        "void main(){" +
                        "   gl_FragColor = vec4(0.0, 1.0, 0.0, 1.0);" +
                        "}",
                GL.GL_TRIANGLES, 0, 3);
    }
}
