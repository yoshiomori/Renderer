package com.example.mori.renderer;

import android.opengl.GLSurfaceView;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Menu principal do app.
 * Created by mori on 06/07/16.
 */
public class Renderer implements GLSurfaceView.Renderer{
    // Dados brutos
    private ArrayList<GLData> datas;
    private ArrayList<GLArray> arrays = new ArrayList<>();

    // Dados processados
    private GlBuffers buffers;
    private ArrayList<GLProgram> programs = new ArrayList<>();

    public Renderer(ArrayList<float[]> arrayList, ArrayList<GLData> datas) {
        this.datas = datas;
        this.buffers = null;
        for (float[] array :
                arrayList) {
            arrays.add(new GLArray(array));
        }
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        loadBuffers();
        loadShaderProgram();
    }

    private void loadBuffers() {
        buffers = new GlBuffers(arrays);
    }

    private void loadShaderProgram() {
        for (GLData data:
                datas) {
            programs.add(new GLProgram(data.getArrayIndex(), data.getAttributes(), data.getUniforms(),
                    data.getVertexShaderCode(), data.getFragmentShaderCode(), data.getMode(),
                    data.getFirst(), data.getCount()));
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GL.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        for (GLProgram program :
                programs) {
            program.render(buffers);
        }
    }
}
