package com.mygdx.game;

/**
 * Created by seth on 3/22/2015.
 */
public interface blueToothInterface
{
    public final int SINGLE = 0x73,
                     ALL    = 0x61,
                     ENABLE = 0x65;
    public boolean connect();
    public boolean isConnected();
    public void sendData(int mode, int red, int green, int blue);
    public void sendData(int mode, int address, int red, int green, int blue);
    public void sendData(int mode);
    public void dispose();


}
