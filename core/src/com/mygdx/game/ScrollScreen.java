package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by seth on 3/30/2015.
 * screen will have a text field and a play button + speed slider
 * color picker will be at bottom
 *
 *
 */
 public class ScrollScreen implements Screen
{
    blueToothInterface b;
    Game game;
    MainScreen mainScreen;
    Skin uiSkin;
    Stage stage;
    SpriteBatch batch;

    ScrollScreen(blueToothInterface b, Game game, MainScreen mainScreen)
    {
        super();
        this.b = b;
        this.game =game;
        this.mainScreen = mainScreen;
    }

    @Override
    public void show()
    {
        batch = new SpriteBatch();
        uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage();


        TextButton back = new TextButton("Back", uiSkin, "default");

        back.setHeight(50);
        back.setWidth(400);
        back.setPosition(75,625);
        back.addListener(new ClickListener(){
                        @Override
                        public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent e, float x, float y)
                        {
                            game.setScreen(mainScreen);
                        }
                    });
        stage.addActor(back);

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        stage.draw();


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

    }
}
