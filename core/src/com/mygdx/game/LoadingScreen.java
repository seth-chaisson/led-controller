package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Font;

/**
 * Created by seth on 3/30/2015.
 */
public class LoadingScreen implements Screen
{

    SpriteBatch spriteBatch;
    BitmapFont bitmapFont;

    @Override
    public void show()
    {
        spriteBatch = new SpriteBatch();
        bitmapFont =  new BitmapFont();
        bitmapFont.setColor(Color.RED);
        bitmapFont.setScale(5.0f);


    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 0, 1);

        spriteBatch.begin();

        bitmapFont.draw(spriteBatch,"Loading...",150, 660);

        spriteBatch.end();




    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose()
    {
       // spriteBatch.dispose();
       // bitmapFont.dispose();
    }
}
