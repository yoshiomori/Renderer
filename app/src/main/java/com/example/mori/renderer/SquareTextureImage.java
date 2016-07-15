package com.example.mori.renderer;

import android.content.Context;

/**
 * Demostração de uso de textura.
 * Created by mori on 12/07/16.
 */
public class SquareTextureImage extends GLImage {
    public SquareTextureImage (Context context) {
        super(context);
        setElementArray(new short[]{
                0, 1, 2,
                2, 1, 3
        });
        setArray(new float[]{
                -1.0f, -1.0f,
                -0.7f, -1.0f,
                -1.0f, -0.7f,
                -0.7f, -0.7f
        });
        setShader(
                "/* Vertex Shader */" +
                        "attribute vec2 vPosition;" +
                        "varying vec2 vTexCoord;" +
                        "void main() {" +
                        "  /* matriz é definida assim mat3(vec3(primeira_coluna), vec3(segunda_coluna), vec3(terceira_coluna)) */" +
                        "  vTexCoord = (mat3(0.5, 0.0, 0.0, 0.0, 0.5, 0.0, 0.5, 0.5, 1.0) * vec3(vPosition, 1.0)).xy;" +
                        "  gl_Position = vec4(vPosition, 0.0, 1.0);" +
                        "}",
                "/* Fragment Shader */" +
                        "precision mediump float;" +
                        "varying vec2 vTexCoord;" +
                        "uniform sampler2D u_texture;" +
                        "void main() {" +
                        "  gl_FragColor = texture2D(u_texture, vTexCoord);" +
                        "}",
                GL.GL_TRIANGLES, 0, 6
        );
        setAttribute("vPosition", false, 0, 0);
        setTexture("u_texture", R.drawable.mesa);
    }

    @Override
    public void onSurfaceChanged(int width, int height) {

    }
}
