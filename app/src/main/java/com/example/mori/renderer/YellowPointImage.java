package com.example.mori.renderer;

import android.content.Context;
import android.opengl.GLES20;

/**
 * Demostração de desenho sem array e element array.
 * Created by mori on 15/07/16.
 */
public class YellowPointImage extends GLImage{
    public YellowPointImage(Context context) {
        super(context);
        setShader(
                "/* Vertex Shader */" +
                        "attribute vec4 position;" +
                        "void main() {" +
                        "   gl_Position = position;" +
                        "   gl_PointSize = 50;" +
                        "}",
                "/* Fragment Shader */" +
                        "precision mediump float;" +
                        "void main() {" +
                        "   gl_FragColor = vec4(1, 1, 0, 1);" +
                        "}",
                GLES20.GL_POINTS, 0, 1
        );
        setAttribute("position", false, 0, new float[]{0f, 0f, 0f, 1f});
    }
}
