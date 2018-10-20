package ru.chat.irokez.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ru.chat.irokez.myapplication.ui.RegisterFragment;
import trikita.log.Log;

public class MainActivityJava extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.simple_fragment_container);

       if(savedInstanceState == null){
           getSupportFragmentManager().beginTransaction()
                   .add(R.id.main_fragment_container, new RegisterFragment())
                   .commit();
       }



       /* // Проверка регистрировался ли пользователь?
        if (checkUserInfo() == true) {
            mFragment = mFragmentManager.findFragmentById(R.id.main_fragment_container);
            if (mFragment == null) {
                mFragment = new RegisterFragment();
            }
            mFragmentManager.beginTransaction().add(R.id.main_fragment_container, mFragment).commit();
            Toast.makeText(getApplicationContext(), "Я думаю что пользователь существует", Toast.LENGTH_LONG).show();
        }
        // Запуск активнсти самого чата
        else {
            startActivity(new Intent(this, ChatActivity.class));
        }*/
        super.onCreate(savedInstanceState);
    }

    private boolean checkUserInfo() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String nick = mSharedPreferences.getString("USER_NICK", "");
        //return !(nick == "");
        return true;
    }
}
