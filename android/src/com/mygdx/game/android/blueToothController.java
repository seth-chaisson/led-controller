package com.mygdx.game.android;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

/**
 * Created by seth on 3/22/2015.
 */
public class blueToothController implements com.mygdx.game.blueToothInterface {

    BluetoothAdapter ba =  null;
    BluetoothDevice led = null;
    BluetoothSocket socket = null;
    OutputStream output = null;

    public boolean connect() {// led mac addr 00:13:12:06:63:71
        // spp magic number uuid?"00001101-0000-1000-8000-00805F9B34FB"

        Thread t = new Thread()
        {   @Override
            public void run()
            {


                while(led == null)
                {
                    ba = BluetoothAdapter.getDefaultAdapter();
                    if (ba == null)
                        Log.d("bt ", "get default adapter failed");

                    Set<BluetoothDevice> pairedDevices;
                    pairedDevices = ba.getBondedDevices();


                    for (BluetoothDevice b : pairedDevices) {
                        if (b.getAddress() == "00:13:12:06:63:71") ;
                        {
                            led = b;

                        }
                    }
                }


                try {
                    while(output == null)
                    {
                        socket = led.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                        socket.connect();
                        output = socket.getOutputStream();
                    }
                } catch (IOException e) {

                    Log.d("bt ", "rf comm socket creation failed");
                }


                sendData(0x73, 72, 50, 0, 0); // set center led to red to confirm link

            }
        };


        t.start();
        return true;

    }

    public boolean isConnected()
    {
        if (output ==null)
            return false;
        else if (led == null)
            return false;
        else if(ba == null)
            return false;

        return true;

    }


    public void sendData(int mode,int address, int red, int green, int blue)
    {
        if (output == null)
            return;

        //Log.d("bt","data written  ("+red +","+green +","+ blue +") \n\n");

        if ( (mode == 0x73)) // if mode =  's'
        {
            try {
                // send a few zeros to synchronize state machine
                output.write(0);output.write(0);output.write(0);output.write(0);output.write(0);


                output.write(mode);
                output.write(address);
                output.write(red);
                output.write(green);
                output.write(blue);
                output.flush();
            } catch (IOException e) {
                Log.d("bt ", "output write s mode failed");
            }
        }
    }

    public void sendData(int mode, int red, int green, int blue) {
        if (output == null)
            return;

        //Log.d("bt","data written  ("+red +","+green +","+ blue +") \n\n");

        if ((mode == 0x61) ) // if mode = 'a'
        {
            try {
                // send a few zeros to synchronize state machine
                output.write(0);output.write(0);output.write(0);output.write(0);output.write(0);


                output.write(mode);
                output.write(red);
                output.write(green);
                output.write(blue);
                output.flush();

                Thread.sleep(3);//


            } catch (IOException e) {
                Log.d("bt ", "output write failed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (mode == 0x65) // if mode = e just send e
        {
            try {
                // send a few zeros to synchronize
                output.write(0);output.write(0);output.write(0);output.write(0);output.write(0);
                output.flush();


                output.write(mode);
                output.flush();
            } catch (IOException e) {
                Log.d("bt ", "output write e mode failed");
            }
        }


    }

    public void sendData(int mode)
    {
        try {
            // send a few zeros to synchronize
            output.write(0);output.write(0);output.write(0);output.write(0);output.write(0);
            output.flush();


            output.write(mode);
            output.flush();
        } catch (IOException e) {
            Log.d("bt ", "output write e mode failed");
        }
    }

    public void dispose()
    {

        sendData(0x61, 0,0,0);// turn off the sign on exit


        try {
            if(output != null)
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(socket != null)
                if(socket.isConnected())
                    socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        ba =  null;
        led = null;
        socket = null;
        output = null;


    }
}
