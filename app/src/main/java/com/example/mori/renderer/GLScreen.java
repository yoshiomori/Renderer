package com.example.mori.renderer;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GLScreen extends GLSurfaceView {
    public GLScreen(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        setRenderer(new GLRenderer(
                new DotImage(getResources()),
                new TrianglesImage(getResources()),
                new DoisPontos(getResources()),
                new SquareBlueImage(getResources()),
                new SquareTextureImage(getResources()),
                new SquareImage(getResources()),
                new YellowPointImage(getResources()),
                new CardImage(getResources())
        ));
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
