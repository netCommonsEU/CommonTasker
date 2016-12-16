package com.example.commontasker.discovery.socketmanagers;


import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.example.commontasker.discovery.Configuration;
import lombok.Getter;
import lombok.Setter;


public class ChatManager implements Runnable {

    private static final String TAG = "ChatHandler";

    private Socket socket = null;
    private final Handler handler;
    @Getter @Setter private boolean disable = false; //attribute to stop or start chatmanager
    private InputStream iStream;
    private OutputStream oStream;

    public ChatManager(@NonNull Socket socket, @NonNull Handler handler) {
        this.socket = socket;
        this.handler = handler;
    }


    @Override
    public void run() {
        Log.d(TAG,"ChatManager started");
        try {
            iStream = socket.getInputStream();
            oStream = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytes;

            //this method's call is used to call handleMessage's case Configuration.FIRSTMESSAGEXCHANGE in the MainActivity.
            handler.obtainMessage(Configuration.FIRSTMESSAGEXCHANGE, this).sendToTarget();

            while (!disable) { //...if enabled
                try {
                    // Read from the InputStream
                    if(iStream!=null) {
                        bytes = iStream.read(buffer);
                        if (bytes == -1) {
                            break;
                        }

                        //this method's call is used to call handleMessage's case Configuration.MESSAGE_READ in the MainActivity.
                        handler.obtainMessage(Configuration.MESSAGE_READ, bytes, -1, buffer).sendToTarget();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "disconnected", e);
                }
            }
        } catch (IOException e) {
            Log.e(TAG,"Exception : " + e.toString());
        } finally {
            try {
                iStream.close();
                socket.close();
            } catch (IOException e) {
                Log.e(TAG,"Exception during close socket or isStream",  e);
            }
        }
    }

    public void write(byte[] buffer) {
        try {
            oStream.write(buffer);
        } catch (IOException e) {
            Log.e(TAG, "Exception during write", e);
        }
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
