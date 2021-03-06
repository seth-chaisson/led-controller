package com.mygdx.game.desktop;

public class desktopBlueToothController implements com.mygdx.game.blueToothInterface
{

    public boolean connect()
    {
        return true;
    }

    @Override
    public boolean isConnected() { return true;  }


    public void sendData(int mode, int red, int green, int blue)
    {
        System.out.println("mode "+mode +", red "+red +", green "+ green+", blue "+ blue+" \n" );
    }
    public void sendData(int mode,int address, int red, int green, int blue)
    {
        System.out.println("mode "+mode +", address "+address+", red "+red +", green "+ green+", blue "+ blue+" \n" );
    }

    @Override
    public void sendData(int mode) {
        System.out.println("mode "+mode  );
    }

    public void dispose()
    {
        System.out.println("disposed\n");
    }
}
