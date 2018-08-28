package br.edu.infnet.icracha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.infnet.icracha.util.LoginHelper;

public class SigninActivity extends AppCompatActivity {

    private EditText mUsername, mPassword;
    private TextView mLinkSignup;
    private LoginHelper mLoginHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mLoginHelper = new LoginHelper();

        mUsername = findViewById(R.id.input_username);
        mPassword = findViewById(R.id.input_password);
        mLinkSignup = findViewById(R.id.link_cadastro);

        Button mBtnSignin = findViewById(R.id.btn_login);

        mBtnSignin.setOnClickListener(login);
        mLinkSignup.setOnClickListener(signup);

    }

    private View.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String username = mUsername.getText().toString().trim();
            String pass = mPassword.getText().toString();

            if(mLoginHelper.validateLogin(username, pass)){

                //setToastMessage("Login efetuado");
                startActivity(new Intent(getApplicationContext(), ManagerActivity.class));
                finish();

            } else {
                setToastMessage("Usuário ou senha incorretos");
            }

        }
    };

    private View.OnClickListener signup = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(), SignupActivity.class));
        }
    };

    private void setToastMessage(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
