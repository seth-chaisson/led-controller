package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;




/**
 * Created by seth on 3/30/2015.
 *
 * this is the next screen after the loading screen
 * will show a few buttons for the other screens
 * draw
 * scroll text
 * animate?
 *
 */
public class MainScreen implements Screen
{
    blueToothInterface b;
    Game game;
    DrawScreen drawScreen;
    ScrollScreen scrollScreen;
    SnakeIntroScreen snakeIntroScreen;
    com.badlogic.gdx.scenes.scene2d.ui.Skin uiSkin;
    Stage   stage;
    SpriteBatch batch;

    MainScreen(blueToothInterface b, Game game)
    {
        super();
        this.b = b;
        this.game = game;
    }

    @Override
    public void show()
    {
        drawScreen = new DrawScreen(b, game, this);
        scrollScreen = new ScrollScreen(b, game, this);
        snakeIntroScreen = new SnakeIntroScreen(b,game, this);

        batch = new SpriteBatch();
        uiSkin = new com.badlogic.gdx.scenes.scene2d.ui.Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage();

        final TextButton draw   = new TextButton("Draw", uiSkin, "default");
        final TextButton scroll = new TextButton("Scroll message", uiSkin, "default");
        final TextButton snake = new TextButton("Snake Game", uiSkin, "default");


        draw.setHeight(50);
        draw.setWidth(400);
        draw.setPosition(75, 620);
        draw.addListener(new ClickListener(){
                            @Override
                            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent e, float x, float y)
                            {
                                game.setScreen(drawScreen);
                            }
                        });

        scroll.setHeight(50);
        scroll.setWidth(400);
        scroll.setPosition(75, 550);
        scroll.addListener(new ClickListener(){
                                @Override
                                public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent e, float x, float y)
                               {
                                    game.setScreen(scrollScreen);
                               }
                           });
        snake.setHeight(50);
        snake.setWidth(400);
        snake.setPosition(75,480);
        snake.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent e, float x, float y)
                        {
                            game.setScreen(snakeIntroScreen);
                        }
        });


        stage.addActor(draw);
        stage.addActor(scroll);
        stage.addActor(snake);

        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);


        batch.begin();
        stage.draw();
        batch.end();

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
