package com.example.mori.mainmenu;

import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Menu principal do app.
 * Created by mori on 06/07/16.
 */
public class MainMenu implements GLSurfaceView.Renderer{


    private final Bitmap mesa;
    private GLProgram programObject;
    private GlBuffer bufferObjects;
    private GLProgram programObject1;
    private GLProgram programObject2;
    private GLProgram programObject3;
    private GLProgram programObject4;
    private GLProgram programObject5;
    private GLTexture textureObjects;
    private GLProgram programObject6;
    private GLProgram programObject7;

    public MainMenu(Bitmap mesa) {
        this.mesa = mesa;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        loadBufferObjects();
        loadTextureObjects();
        loadProgramObjects();
    }

    private void loadBufferObjects() {
        bufferObjects = new GlBuffer(5);
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
        bufferObjects.set(4, new float[]{
                -1.0f, 1.0f,
                1.0f, 1.0f,
                -1.0f, -1.0f,
                1.0f, -1.0f});
    }

    private void loadTextureObjects() {
        textureObjects = new GLTexture(1);
        textureObjects.set(0, mesa);
    }

    private void loadProgramObjects() {
        programObject = new GLProgram(
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

        programObject1 = new GLProgram(
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

        programObject2 = new GLProgram(
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

        programObject3 = new GLProgram(
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

        programObject4 = new GLProgram(
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

        programObject5 = new GLProgram(
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
                        "}");

        programObject6 = new GLProgram(
                "/* Vertex Shader */" +
                        "attribute vec2 vPosition;" +
                        "void main() {" +
                        "  gl_Position = vec4(vPosition, 0.0, 1.0);" +
                        "  gl_PointSize = 50;" +
                        "}",
                "/* Fragment Shader */" +
                        "precision mediump float;" +
                        "void main() {" +
                        "  gl_FragColor = vec4(1.0, 0.0, 1.0, 1.0);" +
                        "}");

        programObject7 = new GLProgram(
                "/* Vertex Shader */" +
                        "attribute vec2 vPosition;" +
                        "uniform mat3 transf_pos_tex;" +
                        "varying vec2 vTexCoord;" +
                        "void main() {" +
                        "  /* matriz é definida assim mat3(vec3(primeira_coluna), vec3(segunda_coluna), vec3(terceira_coluna)) */" +
                        "  vTexCoord = (transf_pos_tex * vec3(vPosition, 1.0)).xy;" +
                        "  gl_Position = vec4(vPosition, 0.0, 1.0);" +
                        "}",
                "/* Fragment Shader */" +
                        "precision mediump float;" +
                        "varying vec2 vTexCoord;" +
                        "uniform sampler2D u_texture;" +
                        "void main() {" +
                        "  gl_FragColor = texture2D(u_texture, vTexCoord);" +
                        "}");
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GL.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);


        // Desenhando com buffer array, element array buffer, uniform, em outro programa e com textura
        programObject5.install();

        bufferObjects.bindArray(4);
        programObject5.defineAttribute("vPosition", 0, 0);

        GL.glActiveTexture(GL.GL_TEXTURE0);
        textureObjects.bindTexture2D(0);
        programObject5.defineUniform("u_texture", 0);

        programObject5.enableActiveAttributes();
        bufferObjects.bindElementArray(3);
        GL.glDrawElements(GL.GL_TRIANGLES, 6, GL.GL_UNSIGNED_SHORT, 0);
        programObject5.disableActiveAttributes();
        bufferObjects.unbindArray();
        bufferObjects.unbindElementArray();
        textureObjects.unbindTexture2D();


        // Desenhando com buffer array
        programObject.install();

        bufferObjects.bindArray(0);
        programObject.defineAttribute("vPosition", 0, 0);

        programObject.enableActiveAttributes();
        GL.glDrawArrays(GL.GL_POINTS, 0, 1);
        programObject.disableActiveAttributes();
        bufferObjects.unbindArray();


        // Desenhando com buffer array e outro programa objeto
        programObject1.install();

        bufferObjects.bindArray(1);
        programObject1.defineAttribute("vPosition", 0, 0);

        programObject1.enableActiveAttributes();
        GL.glDrawArrays(GL.GL_POINTS, 0, 1);
        programObject1.disableActiveAttributes();
        bufferObjects.unbindArray();


        // Desenhando sem buffer array, com elementos de vértice e outro programa objeto
        programObject2.install();

        programObject2.defineAttribute("vPosition", 0, GlBuffer.adaptData(new float[]{
                        -0.5f, 0.5f,
                        0.5f, 0.5f,
                        -0.5f, -0.5f,
                        0.5f, -0.5f}));

        programObject2.enableActiveAttributes();
        GL.glDrawElements(GL.GL_TRIANGLES, 6, GL.GL_UNSIGNED_SHORT,
                GlBuffer.adaptData(new short[]{
                        0, 3, 2,
                        1, 0, 3}));
        programObject2.disableActiveAttributes();


        // Desenhando com buffer array, com elementos de vértices e no mesmo programa objeto
        bufferObjects.bindArray(2);
        programObject2.defineAttribute("vPosition", 0, 0);

        programObject2.enableActiveAttributes();
        GL.glDrawElements(GL.GL_TRIANGLES, 6, GL.GL_UNSIGNED_SHORT,
                GlBuffer.adaptData(new short[]{
                        0, 3, 2,
                        1, 0, 3}));
        programObject2.disableActiveAttributes();
        bufferObjects.unbindArray();


        // Desenhando com buffer array, element array buffer e em outro programa objeto
        programObject3.install();

        bufferObjects.bindArray(2);
        programObject3.defineAttribute("vPosition", 0, 0);

        programObject3.enableActiveAttributes();
        bufferObjects.bindElementArray(3);
        GL.glDrawElements(GL.GL_TRIANGLES, 6, GL.GL_UNSIGNED_SHORT, 0);
        programObject3.disableActiveAttributes();
        bufferObjects.unbindArray();
        bufferObjects.unbindElementArray();


        // Desenhando com buffer array, element array buffer, uniform e em outro programa
        programObject4.install();

        bufferObjects.bindArray(2);
        programObject4.defineAttribute("vPosition", 0, 0);

        programObject4.defineUniform("vColor", new float[]{0.0f, 0.0f, 1.0f, 1.0f});

        programObject4.enableActiveAttributes();
        bufferObjects.bindElementArray(3);
        GL.glDrawElements(GL.GL_TRIANGLES, 6, GL.GL_UNSIGNED_SHORT, 0);
        programObject4.disableActiveAttributes();
        bufferObjects.unbindArray();
        bufferObjects.unbindElementArray();


        // Desenhando sem array buffer, sem element array buffer, sem texture
        programObject6.install();

        programObject6.defineAttribute("vPosition", 0, GlBuffer.adaptData(new float[]{0.0f, 0.0f}));

        programObject6.enableActiveAttributes();
        GL.glDrawArrays(GL.GL_POINTS, 0, 1);
        programObject6.disableActiveAttributes();


        // Desenhando com array buffer, com element array, com uniform e textura
        programObject7.install();

        bufferObjects.bindArray(2);
        programObject7.defineAttribute("vPosition", 0, 0);

        GL.glActiveTexture(GL.GL_TEXTURE0);
        textureObjects.bindTexture2D(0);
        programObject7.defineUniform("u_texture", 0);

        programObject7.defineUniform("transf_pos_tex", new float[]{0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f, 1.0f});

        programObject7.enableActiveAttributes();
        bufferObjects.bindElementArray(3);
        GL.glDrawElements(GL.GL_TRIANGLES, 6, GL.GL_UNSIGNED_SHORT, 0);
        programObject7.disableActiveAttributes();
        bufferObjects.unbindElementArray();
        bufferObjects.unbindArray();
    }
}
