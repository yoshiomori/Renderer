package com.example.mori.renderer;


import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GLScreen screen = new GLScreen(this);
        screen.setImages(
                new DotImage(),
                new TrianglesImage(),
                new DoisPontos(),
                new SquareBlueImage(),
                new SquareTextureImage(),
                new SquareImage(),
                new YellowPointImage(),
                new CardImage(),
                new TwoDots()
        );
        setContentView(screen);
    }
}
