package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.EventListener;

// 960 x 540

public class MyGdxGame extends Game  {

    private final blueToothInterface b;
    DrawScreen drawScreen;
    LoadingScreen loadingScreen;




    public MyGdxGame(blueToothInterface b) {
        super();
        this.b = b;

    }

    @Override
    public void create() {




        b.connect();

        loadingScreen = new LoadingScreen(b);
        setScreen(loadingScreen);


        drawScreen = new DrawScreen(b);





    }

    @Override
    public void render()
    {

        //drawScreen.render(Gdx.graphics.getDeltaTime());
        getScreen().render(Gdx.graphics.getDeltaTime());
        if(b.isConnected() && (getScreen() == loadingScreen))
        {
            loadingScreen.hide();
            Gdx.input.setInputProcessor(drawScreen);
            setScreen(drawScreen);
        }

    }

    @Override
    public void pause()
    {
        dispose();
    }
    @Override
    public void dispose()
    {
        b.dispose();
        drawScreen.dispose();
        loadingScreen.dispose();
    }

}

