package ru.chat.irokez.myapplication.logic;

import android.content.Context;
import android.widget.Toast;

import java.net.Socket;

public class ClientConnection extends Thread {

    private Socket mSocket;
    private Context mContext;

    ClientConnection(Socket socket, Context ctx) {
        mSocket = socket;
        mContext = ctx;
    }

    @Override
    public void run() {
        Toast.makeText(mContext, "Новое соединение созданно", Toast.LENGTH_LONG).show();
        super.run();
    }
}
