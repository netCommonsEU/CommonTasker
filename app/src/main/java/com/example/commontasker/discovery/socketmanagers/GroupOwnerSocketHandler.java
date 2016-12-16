
package com.example.commontasker.discovery.socketmanagers;



import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.commontasker.discovery.Configuration;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.Getter;


public class GroupOwnerSocketHandler extends Thread {

    private static final String TAG = "GroupOwnerSocketHandler";

    private ServerSocket socket = null;
    private Handler handler;
    @Getter InetAddress ipAddress;


    public GroupOwnerSocketHandler(@NonNull Handler handler) throws IOException {
        try {
            socket = new ServerSocket(Configuration.GROUPOWNER_PORT);
            this.handler = handler;
            Log.d("GroupOwnerSocketHandler", "Socket Started");
        } catch (IOException e) {
            Log.e(TAG, "IOException during open ServerSockets with port 4545", e);
            pool.shutdownNow();
            throw e;
        }

    }


    private final ThreadPoolExecutor pool = new ThreadPoolExecutor(
            Configuration.THREAD_COUNT, Configuration.THREAD_COUNT,
            Configuration.THREAD_POOL_EXECUTOR_KEEP_ALIVE_TIME, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>());


    public void closeSocketAndKillThisThread() {
        if(socket!=null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                Log.e(TAG, "IOException during close Socket", e);
            }
            pool.shutdown();
        }
    }


    @Override
    public void run() {
        while (true) {
            try {

                if(socket!=null && !socket.isClosed()) {
                    Socket clientSocket = socket.accept(); //because now i'm connected with the client/peer device
                    pool.execute(new ChatManager(clientSocket, handler));
                    ipAddress = clientSocket.getInetAddress();
                    Log.d(TAG, "Launching the I/O handler");
                }
            } catch (IOException e) {
                //if there is an exception, after closing socket and pool, the execution stops with a "break".
                try {
                    if (socket != null && !socket.isClosed()) {
                        socket.close();
                    }
                } catch (IOException ioe) {
                    Log.e(TAG, "IOException during close Socket", ioe);
                }
                pool.shutdownNow();
                break; //stop the while(true).
            }
        }
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }
}
