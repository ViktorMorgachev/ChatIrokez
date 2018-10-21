package ru.chat.irokez.myapplication.logic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;

import trikita.log.Log;

// Сервер с определённым открытым портом
public class ChatService extends Service {

    private ServerSocket mSocketListener;
    // если сервер работает при удачном открытом порте
    private final int serverPort = (int) (Math.random() * 1000);
    private BufferedReader mBufferedReader;
    private BufferedWriter mBufferedWriter;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Служба запущена:" + serverPort,
                Toast.LENGTH_SHORT).show();
        try {
            mSocketListener = new ServerSocket(serverPort);
            Log.e("Порт сервера открыт: " + serverPort);
            while (true){

            }
        } catch (IOException e) {
            Log.e("Ошибка открытия прослушаемого порта");
            e.printStackTrace();
        }

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if (mSocketListener != null)
            mSocketListener.close();
        } catch (IOException e) {
            Log.e("Ошибка закрытия прослушаемого порта");
            e.printStackTrace();
        }
        Toast.makeText(this, "Служба остановлена",
                Toast.LENGTH_SHORT).show();
    }
}
