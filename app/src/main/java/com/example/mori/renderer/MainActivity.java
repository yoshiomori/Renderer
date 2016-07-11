package com.example.mori.renderer;


import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ArrayList<GLImage> images = new ArrayList<>();
        images.add(new DoisPontos());
        images.add(new SquareImage());
        images.add(new TrianglesImage());
        images.add(new DotImage());

        ArrayList<GLArray> arrays = new ArrayList<>();

        ArrayList<GLData> datas = new ArrayList<>();

        for (GLImage image :
                images) {
            if (image.getArrays() != null) {
                image.getData().setArrayIndex(arrays.size());
                arrays.addAll(image.getArrays());
            }
            if (image.getData() == null)
                throw new RuntimeException("getData retornando null");
            datas.add(image.getData());
        }

        GLSurfaceView screen = new GLSurfaceView(this);
        screen.setEGLContextClientVersion(2);
        screen.setRenderer(new Renderer(arrays, datas));
        screen.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(screen);
    }
}
