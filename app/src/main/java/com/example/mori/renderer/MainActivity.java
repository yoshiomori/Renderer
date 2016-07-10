package com.example.mori.renderer;


import android.app.Activity;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ArrayList<GLImage> images = new ArrayList<>();
        images.add(new RetangleImage());
        images.add(new DotImage());

        ArrayList<float[]> arrays = new ArrayList<>();

        ArrayList<GLData> datas = new ArrayList<>();

        for (GLImage image :
                images) {
            for (GLData data :
                    image.getDatas()) {
                data.setArrayIndex(data.getArrayIndex() + arrays.size());
            }
            arrays.addAll(image.getArrays());
            datas.addAll(image.getDatas());
        }

        GLSurfaceView screen = new GLSurfaceView(this);
        screen.setEGLContextClientVersion(2);
        screen.setRenderer(new Renderer(arrays, datas));
        screen.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(screen);
    }
}
