package br.edu.infnet.icracha;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.edu.infnet.icracha.user.User;
import br.edu.infnet.icracha.util.LoginHelper;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private LoginHelper loginHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            loginHelper = new LoginHelper(currentUser.getUid());
            entrarApp();
        } else {
            irParaLogin();
        }
    }

    private void irParaLogin() {

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    private void entrarApp() {

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                User user = loginHelper.getUser();

                Intent intent = new Intent(getApplicationContext(), ManagerActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
