package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 * Created by seth on 3/29/2015.
 */
public class DrawScreen implements Screen, InputProcessor
{
    blueToothInterface b;
    SpriteBatch batch;
    Texture img;
    Pixmap colorWheelPixmap;

    ShapeRenderer shapeRenderer;
    Array<pixel> ledArray;
    Rectangle colorPreviewBox;
    com.badlogic.gdx.graphics.Color colorPreviewColor;

    DrawScreen(blueToothInterface b)
    {
        this.b = b;
        batch = new SpriteBatch();
        img = new Texture("colorWheel.jpg");
        img.getTextureData().prepare();
        colorWheelPixmap = img.getTextureData().consumePixmap();


        shapeRenderer = new ShapeRenderer();
        ledArray = new Array<pixel>(true,49, pixel.class);

        for(int row = 0; row < 7; row++)
        {
            for(int col=0; col < 7; col ++)
            {
                ledArray.add(new pixel(60 + (col*60),(900 - (row*60)), Color.GRAY));
            }
        }

        colorPreviewBox = new Rectangle(470, 850, 60, 60);
        colorPreviewColor    = com.badlogic.gdx.graphics.Color.WHITE;
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {


        Gdx.gl.glClearColor(0, 0, 0, 1);
        //Gdx.gl.glClearColor(((float)red/255f), ((float)green/255f) ,((float)blue/255f), 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        //batch.draw(img, 0, 0);
        batch.draw(img, 0, 0, 540, 540);
        batch.end();


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // draw led array
        for(pixel p : ledArray)
            p.drawPixel(shapeRenderer);
        //draw preview box
        shapeRenderer.setColor(colorPreviewColor);
        shapeRenderer.rect(colorPreviewBox.x, colorPreviewBox.y,colorPreviewBox.getWidth(),colorPreviewBox.getHeight());


        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {

        img.dispose();
        //if(batch != null)
        // batch.dispose();
        ledArray.clear();
        //shapeRenderer.dispose();
    }



    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        screenY = (Gdx.graphics.getHeight() - screenY); // convert to the same cordinates as the graphics
        // gdx graphics coordinates: origin at bottom left
        // pixmap coordinates      : origin top left
        // touch screenx/y         : origin top left


        //if the thouch was a ledArray object

        for(pixel p : ledArray)
        {
            if(p.bounds.contains(screenX,screenY) )
            {

                p.setPixelColor(colorPreviewColor);
                int color = Color.rgb888(colorPreviewColor);
                b.sendData(0x73, ledArray.indexOf(p,true)*3,((color&0xff0000)>>16), ((color&0x00ff00)>>8),((color&0xff)) );
            }
        }

        // if the touch is within 240 pixels of the center of the color wheel(270,270)
        if((Math.sqrt(    ( (Math.pow((screenX-270), 2)) + (Math.pow((screenY-270), 2))   )   ))  < 225)
        {
            //find its color

            colorPreviewColor = new com.badlogic.gdx.graphics.Color(colorWheelPixmap.getPixel(
                    ((screenX*200)/540)     ,
                    (200-((screenY*200)/540))    ));

            System.out.println("color " + Color.rgb888(colorPreviewColor));

            int color = Color.rgb888(colorPreviewColor);

            //b.sendData(0x61, ((color&0xff0000)>>16), ((color&0x00ff00)>>8),((color&0xff)) );
            System.out.println("touch distance: "+Math.sqrt(    ( (Math.pow((screenX-270), 2)) + (Math.pow((screenY-270), 2))   )   ));


        }


        System.out.println("screenx:"+ screenX +"");
        System.out.println("screeny:"+ screenY +"");



        System.out.println("color:" + colorPreviewColor.toString() + "\n");




        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        screenY = (Gdx.graphics.getHeight() - screenY); // convert to the same cordinates as the graphics
        // gdx graphics coordinates: origin at bottom left
        // pixmap coordinates      : origin top left
        // touch screenx/y         : origin top left

        //if the thouch was a ledArray object
        for(pixel p : ledArray)
        {
            if(p.bounds.contains(screenX,screenY) )
            {

                p.setPixelColor(colorPreviewColor);
                int color = Color.rgb888(colorPreviewColor);
                b.sendData(0x73, ledArray.indexOf(p,true)*3,((color&0xff0000)>>16), ((color&0x00ff00)>>8),((color&0x0000ff)) );
            }
        }
        // if the touch is within 240 pixels of the center of the color wheel(270,270)
        if((Math.sqrt(    ( (Math.pow((screenX-270), 2)) + (Math.pow((screenY-270), 2))   )   ))  < 225)
        {
            //find its color

            colorPreviewColor = new com.badlogic.gdx.graphics.Color(colorWheelPixmap.getPixel(
                    ((screenX*200)/540)     ,
                    (200-((screenY*200)/540))    ));

            System.out.println("color " + Color.rgb888(colorPreviewColor));

            int color = Color.rgb888(colorPreviewColor);

            //b.sendData(0x61, ((color&0xff0000)>>16), ((color&0x00ff00)>>8),((color&0xff)) );
            System.out.println("touch distance: "+Math.sqrt(    ( (Math.pow((screenX-270), 2)) + (Math.pow((screenY-270), 2))   )   ));


        }


        System.out.println("screenx:"+ screenX +"");
        System.out.println("screeny:"+ screenY +"");



        System.out.println("color:" + colorPreviewColor.toString() + "\n");



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
