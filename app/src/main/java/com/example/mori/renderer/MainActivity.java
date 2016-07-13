package com.example.mori.renderer;


import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GLSurfaceView screen = new GLSurfaceView(this);
        screen.setEGLContextClientVersion(2);
        screen.setRenderer(new Renderer(
                new DotImage(getApplicationContext()),
                new TrianglesImage(getApplicationContext()),
                new DoisPontos(getApplicationContext()),
                new SquareBlueImage(getApplicationContext()),
                new SquareTextureImage(getApplicationContext()),
                new SquareImage(getApplicationContext())
        ));
        screen.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(screen);
    }
}
