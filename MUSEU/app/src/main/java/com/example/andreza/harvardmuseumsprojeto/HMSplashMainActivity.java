package com.example.andreza.harvardmuseumsprojeto;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class HMSplashMainActivity extends AppCompatActivity {


    private final static int TIME_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hmsplash_main);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                irParaLogin();
            }
        }, 3000);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }, 3000);
//        });


    }

    public void irParaLogin(){
        Toast.makeText(this, "Finalizado o Splash", Toast.LENGTH_SHORT).show();
    }
}
