package com.example.mori.renderer;


import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GLScreen screen = new GLScreen(this);
        setContentView(screen);
    }
}
