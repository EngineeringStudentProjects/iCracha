package br.edu.infnet.icracha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.edu.infnet.icracha.DAO.UserDAO;
import br.edu.infnet.icracha.user.User;
import br.edu.infnet.icracha.util.CpfUtil;
import br.edu.infnet.icracha.util.HashHandler;
import br.edu.infnet.icracha.util.LoginHelper;

import static br.edu.infnet.icracha.util.ValidateFields.validateEmail;
import static br.edu.infnet.icracha.util.ValidateFields.validateName;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText mEdtTxtCpf, mEdtTxtName, mEdtTxtUser,
                    mEdtTxtPass, mEdtTxtPassConfirm;
    private UserDAO userDAO;
    private LoginHelper mLoginHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        userDAO = new UserDAO();
        //mLoginHelper = new LoginHelper();

        mEdtTxtCpf = findViewById(R.id.edt_txt_cpf);
        mEdtTxtName = findViewById(R.id.edt_txt_name);
        mEdtTxtUser = findViewById(R.id.edt_txt_user);
        mEdtTxtPass = findViewById(R.id.edt_txt_password);
        mEdtTxtPassConfirm = findViewById(R.id.edt_txt_password_confirm);

        //Máscara CPF
        SimpleMaskFormatter simpleMaskCpf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher maskCpf = new MaskTextWatcher(mEdtTxtCpf, simpleMaskCpf);
        mEdtTxtCpf.addTextChangedListener(maskCpf);

        Button btnSignup = findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(register);
    }

    private View.OnClickListener register = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final String cpf = mEdtTxtCpf.getText().toString().trim();
            final String name = mEdtTxtName.getText().toString().trim();
            final String email = mEdtTxtUser.getText().toString().trim();
            final String pass = mEdtTxtPass.getText().toString();
            String passConfirm = mEdtTxtPassConfirm.getText().toString();

            //Validação dos campos em branco
            if(cpf.isEmpty() || name.isEmpty() || email.isEmpty() ||
                    pass.isEmpty() || passConfirm.isEmpty()){
                setToastMessage("Campos não podem estar em branco");
            } else {
                //Validação CPF
                if(!CpfUtil.isValid(cpf)) {
                    setToastMessage("CPF inválido");
                //Valida Nome
                }else if(!validateName(name)){
                    setToastMessage("Nome não pode ter caracteres especiais");
                //Valida email
                } else if(!validateEmail(email)){
                    setToastMessage("Email inválido");
                //Verifica se Senhas Conferem
                } else if(!pass.contentEquals(passConfirm)){
                    setToastMessage("Senhas Não Conferem");
                } else {
                    /*User user = new User(cpf, name, email, HashHandler.hashedString(pass));
                    if(mLoginHelper.userExists(user)){
                        setToastMessage("Usuário já existe");
                    } else {
                        userDAO.salvar(user);
                        setToastMessage("Cadastro efetuado");
                        finish();
                    }*/

                    mAuth.createUserWithEmailAndPassword(email, HashHandler.hashedString(pass))
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        if(user != null){
                                            User usuario = new User(
                                                    user.getUid(),
                                                    cpf,
                                                    name,
                                                    email,
                                                    HashHandler.hashedString(pass)
                                            );

                                            userDAO.salvar(usuario);

                                            Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }

                                    } else {
                                        setToastMessage("Cadastro não realizado.");
                                    }
                                }

                            });

                }
            }

        }
    };

    private void setToastMessage(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
