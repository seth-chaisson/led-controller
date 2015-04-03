package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;


// 960 x 540

public class MyGdxGame extends Game  {

    private final blueToothInterface b;

    LoadingScreen loadingScreen;
    MainScreen mainScreen;




    public MyGdxGame(blueToothInterface b) {
        super();
        this.b = b;

    }

    @Override
    public void create() {




        b.connect();

        loadingScreen = new LoadingScreen();
        setScreen(loadingScreen);

        mainScreen = new MainScreen(b, this);





    }

    @Override
    public void render()
    {

        //drawScreen.render(Gdx.graphics.getDeltaTime());
        getScreen().render(Gdx.graphics.getDeltaTime());
        if(b.isConnected() && (getScreen() == loadingScreen))
        {
            loadingScreen.hide();
            loadingScreen.dispose();
            setScreen(mainScreen);

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


    }

}

