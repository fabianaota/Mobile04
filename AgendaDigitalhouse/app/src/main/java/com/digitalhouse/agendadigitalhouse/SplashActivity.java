package com.digitalhouse.agendadigitalhouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                irParaMain();
            }
        }, 3000);

    }

    public void irParaMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
