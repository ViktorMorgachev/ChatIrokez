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
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import trikita.log.Log;

// Сервер с определённым открытым портом
public class ChatService extends Service {

    private ServerSocket mSocketListener;
    // если сервер работает при удачном открытом порте
    private int serverPort;
    private BufferedReader mBufferedReader;
    private BufferedWriter mBufferedWriter;
    // Количество открытых соединений (static если служба пересоздастся, то пул соединений сохранится)
    private static List<ClientConnection> mClientConnections;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        if (mClientConnections == null) {
            mClientConnections = new ArrayList<>();
        }
/*
        try {
            serverPort = (int) (Math.random() * 1000);
            for (int i = serverPort; i < 50000; i++) {
                if (isAvailablePort(i))
                    break;
            }
            mSocketListener = new ServerSocket(serverPort);
            Log.d("Порт сервера открыт: " + serverPort);
        } catch (IOException e) {
            Log.e("Ошибка открытия прослушаемого порта:" + serverPort);
            e.printStackTrace();

        }*/
    }


    private static boolean isAvailablePort(int port) {
        try (ServerSocket ignored = new ServerSocket(port)) {
            return false;
        } catch (IOException ignored) {
            return true;
        }
    }

    // Допустим пока что порт всегда открывается удачно
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        try {
            mSocketListener = new ServerSocket((int) (Math.random() * 1000));
            Toast.makeText(this, "Служба запущена:" + serverPort,
                    Toast.LENGTH_SHORT).show();

            while (true) {
                Socket socketClient = mSocketListener.accept();
                ClientConnection clientConnection = new ClientConnection(socketClient, getApplicationContext());
                mClientConnections.add(clientConnection);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Ошибка прослушивания входящих соединений");
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
