package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import java.util.LinkedList;

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
    Touchpad touchPad;
    Touchpad.TouchpadStyle touchpadStyle;
    Skin    touchPadSkin;
    Drawable touchPadBackground;
    Drawable touchPadKnob;
    float touchPadThreshold =0.75f;

    Boolean gameOver = false;
    final   int     UP    = 0,
                    DOWN  = 1,
                    LEFT  = 2,
                    RIGHT = 3,
                    STOP  = 4;
    int     direction = RIGHT;
    float           time    = 0,
                    gameTick= 1.0f;
    Color            foodColor = Color.RED,
                    snakeColor = Color.ORANGE;
    LinkedList <Vector2> board;



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
        board = new LinkedList<Vector2>();
        board.add(new Vector2(3, 3)); // first element is the food
        board.addLast(new Vector2(0, 0));      // second element is the head

        stage = new Stage();
        spriteBatch = new SpriteBatch();
        uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.GREEN);


        restart = new TextButton("New Game", uiSkin, "default");
        mainMenu = new TextButton("Quit", uiSkin, "default");





        mainMenu.setHeight(50);
        mainMenu.setWidth(400);
        mainMenu.setPosition(75, 250);
        mainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y)
            {
                game.setScreen(mainScreen);
            }
        });
        restart.setHeight(50);
        restart.setWidth(400);
        restart.setPosition(75, 180);
        restart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y)
            {

                game.setScreen(snakeIntroScreen);
            }
        });

        touchPadSkin = new Skin();
        touchPadSkin.add("touchbackground", new Texture("touchBackground.png"));
        touchPadSkin.add("touchknob", new Texture("touchKnob.png"));
        touchpadStyle = new Touchpad.TouchpadStyle();

        touchPadBackground = touchPadSkin.getTiledDrawable("touchbackground");
        touchPadKnob = touchPadSkin.getTiledDrawable("touchknob");

        touchpadStyle.background = touchPadBackground;
        touchpadStyle.knob = touchPadKnob;

        touchPad = new Touchpad(10, touchpadStyle);
        touchPad.setBounds(15,15,200,200);
        touchPad.setPosition(170,380);


        stage.addActor(restart);
        stage.addActor(mainMenu);
        stage.addActor(touchPad);


        Gdx.input.setInputProcessor(stage);




    }

    @Override
    public void render(float delta)
    {

        time += delta;
        if(gameOver)
        {
            //show game over
            //stage.addActor(textlabel with score);
            snakeColor = Color.RED;
        }
        else if(time>gameTick && !gameOver)
        {
            updateGame();
            updateLeds();
            time =0;
        }





        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);




        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    public void updateGame()
    {

        //read input
        if(touchPad.getKnobPercentX() > touchPadThreshold)
            setDirection(RIGHT);
        else if(touchPad.getKnobPercentX() < (-touchPadThreshold))
            setDirection(LEFT);
        if(touchPad.getKnobPercentY() > touchPadThreshold)
            setDirection(UP);
        else if(touchPad.getKnobPercentY() < (-touchPadThreshold))
            setDirection(DOWN);
        Gdx.app.log("touchpad", "touchpadx:" + touchPad.getKnobPercentX());
        Gdx.app.log("touchpad", "touchpady:" + touchPad.getKnobPercentY());

        Vector2 nextPosition = null;
        // game logic goes here


        if(direction == UP)
            nextPosition = new Vector2(board.get(1).x , board.get(1).y-1);
        else if(direction == DOWN)
            nextPosition = new Vector2(board.get(1).x , board.get(1).y+1);
        else if(direction == LEFT)
            nextPosition = new Vector2(board.get(1).x -1, board.get(1).y);
        else if(direction == RIGHT)
            nextPosition = new Vector2(board.get(1).x +1, board.get(1).y);

        Gdx.app.log("snake", "food:" + board.get(0).toString());
        Gdx.app.log("snake", "head:" + board.get(1).toString());
        Gdx.app.log("snake", "next:" + nextPosition);




        // if you hit the wall game over
        if(nextPosition.x > 6 || nextPosition.x < 0)
        {
            gameOver = true;
            Gdx.app.log("snake", "off wall side");
            return;

        }
        if(nextPosition.y > 6 || nextPosition.y < 0)
        {
            gameOver = true;
            Gdx.app.log("snake", "off wall vertical");
            return;

        }

        // if you hit the food
        if(board.get(0).x == nextPosition.x && board.get(0).y == nextPosition.y)
        {
            gameTick -= .05;
            board.add(1,nextPosition); // move the head
            // tail stays to grow snake

            //find empty random place to put next food
            nextPosition = null;
            while(nextPosition == null)
            {
                // pick a random position
                nextPosition = new Vector2(  (Math.round(Math.random() * 6)),(Math.round(Math.random()*6)) );
                if(board.contains(nextPosition))//the space is not empyt
                {
                    nextPosition = null;
                    continue;
                }
                else
                {
                    board.remove(0);// remove old food position
                    board.addFirst(nextPosition); //add new food
                }

            }
            Gdx.app.log("snake", "food");
            return;

        }
        //if you hit your tail game over
        else if(board.contains(nextPosition) )
        {
            gameOver = true;
            Gdx.app.log("snake", "hit tail");
            return;

        }
        // if you hit an empty space
        else
        {
            board.add(1, nextPosition); // move the head
            board.removeLast();         //delete the tail
            Gdx.app.log("snake", "empty space");
            return;
        }








    }

    public void updateLeds()
    {
        b.sendData(blueToothInterface.ENABLE); // pauses the led refresh
        b.sendData(blueToothInterface.ALL,10,10,10); // clear


        // abgr to rgb
        b.sendData(blueToothInterface.SINGLE,
                (int)((board.get(0).x * 3) + (board.get(0).y * 21)),
                ((foodColor.toIntBits()&0x0000ff)),((foodColor.toIntBits()&0x00ff00)>>8),((foodColor.toIntBits()&0xff0000)>>16)
                    ); // send food position

        for(int x =1; x < board.size(); x++ )
            b.sendData(blueToothInterface.SINGLE,
                    (int)((board.get(x).x * 3) + (board.get(x).y * 21)),
                    ((snakeColor.toIntBits()&0x0000ff)),((snakeColor.toIntBits()&0x00ff00)>>8),((snakeColor.toIntBits()&0xff0000)>>16)
                    );

        Gdx.app.log("color", "snake:("+((foodColor.toIntBits()&0xff0000)>>16)+")("+((foodColor.toIntBits()&0x00ff00)>>8)+")("+((foodColor.toIntBits()&0x0000ff))+")"+foodColor.toIntBits() );

        b.sendData(blueToothInterface.ENABLE); // resume led refresh
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
        Gdx.app.log("screen", "snake screen disposed");
    }
}
