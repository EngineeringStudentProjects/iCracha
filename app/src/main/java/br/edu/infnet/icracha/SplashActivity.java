package br.edu.infnet.icracha;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                irParaLogin();
            }
        }, 3000);

    }

    private void irParaLogin() {
        Intent intent = new Intent(this, SigninActivity.class);
        startActivity(intent);
        finish();
    }
}
