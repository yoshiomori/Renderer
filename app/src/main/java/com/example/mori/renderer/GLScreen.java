package com.example.mori.renderer;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class GLScreen extends GLSurfaceView {
    private final GLRenderer renderer;
    private GLImage[] images;
    private float mPreviousX = 0;
    private float mPreviousY = 0;

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = (getWidth() - 2 * event.getX()) / getWidth();
        float y = (getHeight() - 2 * event.getY()) / getHeight();

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;
                for (GLImage image :
                        images) {
                    image.onMove(dx, dy, x, y);
                }
                break;
            case MotionEvent.ACTION_DOWN:
                for (GLImage image :
                        images) {
                    image.onDown(x, y);
                }
                break;
            case MotionEvent.ACTION_UP:
                for (GLImage image :
                        images) {
                    image.onUp();
                }
                break;
        }

        mPreviousX = x;
        mPreviousY = y;

        requestRender();
        return super.onTouchEvent(event);
    }
}
