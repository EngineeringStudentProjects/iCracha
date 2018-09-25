package br.edu.infnet.icracha;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.edu.infnet.icracha.user.User;
import br.edu.infnet.icracha.util.HashHandler;
import br.edu.infnet.icracha.util.LoginHelper;

import static br.edu.infnet.icracha.util.ValidateFields.validateEmail;

public class SigninActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mUsername, mPassword;
    private TextView mLinkSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();

        mUsername = findViewById(R.id.input_username);
        mPassword = findViewById(R.id.input_password);
        mLinkSignup = findViewById(R.id.link_cadastro);

        Button mBtnSignin = findViewById(R.id.btn_login);

        mBtnSignin.setOnClickListener(login);
        mLinkSignup.setOnClickListener(signup);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
            startActivity(intent);
            finish();
            setToastMessage("Carregando...");

        }
    }

    private View.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String email = mUsername.getText().toString().trim();
            String pass = mPassword.getText().toString();

            if(email.isEmpty() || pass.isEmpty()){
                setToastMessage("Campos não podem estar em branco");
            } else {
                if(!validateEmail(email)) {
                    setToastMessage("Email inválido");
                } else {
                    mAuth.signInWithEmailAndPassword(email, HashHandler.hashedString(pass))
                            .addOnCompleteListener(requisicaoCompleta);
                }
            }


            /*User user = mLoginHelper.validateLogin(email, pass);

            if(user != null){

                //setToastMessage("Login efetuado");
                Intent intent = new Intent(getApplicationContext(), ManagerActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();

            } else {
                setToastMessage("Usuário ou senha incorretos");
            }*/


        }
    };

    private OnCompleteListener<AuthResult> requisicaoCompleta = new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){
                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(intent);
                finish();
            } else {
                setToastMessage("Login não realizado.");

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
