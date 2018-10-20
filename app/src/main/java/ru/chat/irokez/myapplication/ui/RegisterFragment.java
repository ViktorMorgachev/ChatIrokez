package ru.chat.irokez.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Field;

import ru.chat.irokez.myapplication.R;
import trikita.log.Log;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private Button mButton;
    private EditText mEditText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment_layout, null);
        mButton = view.findViewById(R.id.btn_register);
        mEditText = view.findViewById(R.id.et_nick);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        mButton.setOnClickListener(this);
        Log.usePrinter(Log.ANDROID, true).usePrinter(Log.SYSTEM, false);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (!mEditText.getText().toString().equals("")) {
            mSharedPreferences.edit().putString("USER_NICK", mEditText.getText().toString());
       //     startActivity(new Intent(getContext(), ChatActivity.class));
        } else Toast.makeText(getContext(), "Ваш ник разве пустой?", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();



        Log.e("Отсоеденили!!!!");

        /*try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        Log.e("Присоеденили!!!!");
        super.onAttach(context);
    }
}
