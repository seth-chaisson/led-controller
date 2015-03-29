package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by seth on 3/23/2015.
 */
public class pixel
{


    Color pixelColor;
    float x,y;
    private final float pixelRadius = 10;
    public Rectangle bounds;

    public pixel(float x, float y, Color c)
    {
        pixelColor = c;
        this.x = x;
        this.y = y;
        bounds = new Rectangle(x-30,y-30,pixelRadius+50,pixelRadius+50);
    }


    public void drawPixel(ShapeRenderer s)
    {
        s.setColor(pixelColor);
        s.circle(x, y, pixelRadius, 25);
        //s.rect(bounds.x,bounds.y,bounds.width,bounds.height);

    }





    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Color getPixelColor() {
        return pixelColor;
    }

    public void setPixelColor(Color pixelColor) {
        this.pixelColor = pixelColor;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
