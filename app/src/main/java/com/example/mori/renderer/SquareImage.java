package com.example.mori.renderer;

import java.util.ArrayList;

/**
 * Demostração de como desenha com element array.
 * Created by mori on 10/07/16.
 */
public class SquareImage extends GLImage {
    public SquareImage(){
        ArrayList<GLAttribute> attributes = new ArrayList<>();
        attributes.add(new GLAttribute("position", false, 0, new float[]{
                -1.0f, 1.0f, 0.0f, 1.0f,
                -0.7f, 1.0f, 0.0f, 1.0f,
                -1.0f, 0.7f, 0.0f, 1.0f,
                -0.7f, 0.7f, 0.0f, 1.0f
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
                        "   gl_FragColor = vec4(0.0, 1.0, 0.5, 1.0);" +
                        "}",
                GL.GL_TRIANGLES, 6,
                new short[]{
                        0, 1, 2,
                        2, 1, 3
                });
    }
}
