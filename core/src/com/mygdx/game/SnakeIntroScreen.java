package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by seth on 4/2/2015.
 */
public class SnakeIntroScreen implements Screen,InputProcessor
{
    blueToothInterface b;
    Game game;
    MainScreen mainScreen;
    SnakeScreen snakeScreen;

    BitmapFont bitmapFont;
    SpriteBatch spriteBatch;


    SnakeIntroScreen(blueToothInterface b, Game game, MainScreen mainScreen)
    {
        super();
        this.b = b;
        this.game =game;
        this.mainScreen = mainScreen;

    }

    @Override
    public void show() {

        snakeScreen = new SnakeScreen(b,game,mainScreen,this);

        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.GREEN);
        bitmapFont.setScale(4.0f);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta)
    {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        spriteBatch.begin();
        bitmapFont.setScale(4.0f);
        bitmapFont.draw(spriteBatch,"SNAKE", 150,550);
        bitmapFont.setScale(2.0f);
        bitmapFont.draw(spriteBatch,"Tap anywhere to start!", 150,450);

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
    public void dispose() {
        Gdx.app.log("screen", "snake intro screen disposed");

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.BACK)
        {
            game.setScreen(mainScreen);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        // when touched got to the snake screen
        game.setScreen(snakeScreen);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
