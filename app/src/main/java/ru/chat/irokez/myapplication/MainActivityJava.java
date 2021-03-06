package ru.chat.irokez.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.chat.irokez.myapplication.activities.ChatActivity;
import ru.chat.irokez.myapplication.logic.ChatService;
import ru.chat.irokez.myapplication.ui.RegisterFragment;
import trikita.log.Log;

public class MainActivityJava extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_fragment_container);
        Log.d("onCreate " + this.getClass().getCanonicalName().toString());


        if(isHasUser() != true) {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.main_fragment_container, new RegisterFragment())
                        .commit();
            }
        }
        // Запуск активнсти самого чата и сервиса (сервера принимающего запросы от клиентов)
        else {
            startService(new Intent(this, ChatService.class));
            startActivity(new Intent(this, ChatActivity.class));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       stopService(new Intent(this, ChatService.class));
    }


    @Override
    protected void onPause() {
        Log.d("onPause " + this.getClass().getCanonicalName().toString());
        super.onPause();
    }

    private boolean isHasUser() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String nick = mSharedPreferences.getString("USER_NICK", "");
        Log.d("User's nick is " + nick + this.getClass().getCanonicalName().toString());
        return !(nick == "");

    }
}
