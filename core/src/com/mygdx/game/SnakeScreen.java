package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by seth on 4/2/2015.
 */
public class SnakeScreen implements Screen
{

    blueToothInterface b;
    Game game;
    MainScreen mainScreen;
    SnakeIntroScreen snakeIntroScreen;
    Stage stage;
    Skin uiSkin;
    SpriteBatch spriteBatch;
    BitmapFont bitmapFont;
    TextButton restart ;
    TextButton mainMenu ;

    Boolean gameOver = false;
    final   int     UP    = 0,
                    DOWN  = 1,
                    LEFT  = 2,
                    RIGHT = 3,
                    STOP  = 4;
    int     direction = STOP;



    SnakeScreen(blueToothInterface b, Game game, MainScreen mainScreen, SnakeIntroScreen snakeIntroScreen)
    {
        super();
        this.b = b;
        this.game =game;
        this.mainScreen = mainScreen;
        this.snakeIntroScreen = snakeIntroScreen;
    }

    @Override
    public void show()
    {
        stage = new Stage();
        spriteBatch = new SpriteBatch();
        uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.GREEN);

        restart = new TextButton("New Game", uiSkin, "default");
        mainMenu = new TextButton("Quit", uiSkin, "default");
        TextButton up = new TextButton("UP",uiSkin,  "default");

        mainMenu.setHeight(50);
        mainMenu.setWidth(400);
        mainMenu.setPosition(75, 550);
        mainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y)
            {
                game.setScreen(mainScreen);
            }
        });
        restart.setHeight(50);
        restart.setWidth(400);
        restart.setPosition(75, 480);
        restart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y)
            {
                game.setScreen(snakeIntroScreen);
            }
        });

        up.setHeight(50);
        up.setWidth(150);
        up.setPosition(75, 480);
        up.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent e, float x, float y)
                        {
                            setDirection(UP);
                        }
        });

        stage.addActor(up);


        Gdx.input.setInputProcessor(stage);




    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);


        if(gameOver)
        {
            stage.clear();
            stage.addActor(restart);
            stage.addActor(mainMenu);
            //stage.addActor(textlabel with score);
        }

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    public void setDirection(int d)
    {
        //if invalid direction
        //return
        //else
        //direction = d;

        if(direction == d)
            return;
        else if((d == LEFT) && (direction == RIGHT))
            return;
        else if((d == RIGHT) && (direction == LEFT))
            return;
        else if((d == UP) && (direction == DOWN))
            return;
        else if((d == DOWN) && (direction == UP))
            return;

        direction = d;

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
