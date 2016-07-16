package com.example.mori.renderer;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GLScreen extends GLSurfaceView {
    private final GLRenderer renderer;
    private GLImage[] images;

    public GLScreen(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        renderer = new GLRenderer();
        setRenderer(renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void setImages(GLImage... images) {
        for (GLImage image :
                images) {
            image.setResources(getResources());
        }

        renderer.setImages(images);
        this.images = images;
    }
}
