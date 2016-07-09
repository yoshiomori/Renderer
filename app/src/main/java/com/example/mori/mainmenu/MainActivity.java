package com.example.mori.mainmenu;


import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView screen = new GLSurfaceView(this);
        screen.setEGLContextClientVersion(2);
        screen.setRenderer(new MainMenu());
        screen.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(screen);
    }
}
