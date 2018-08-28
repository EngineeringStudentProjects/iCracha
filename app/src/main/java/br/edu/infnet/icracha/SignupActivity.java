package br.edu.infnet.icracha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.infnet.icracha.DAO.UserDAO;
import br.edu.infnet.icracha.user.User;
import br.edu.infnet.icracha.util.CpfUtil;
import br.edu.infnet.icracha.util.HashHandler;
import br.edu.infnet.icracha.util.LoginHelper;

public class SignupActivity extends AppCompatActivity {

    private EditText mEdtTxtCpf, mEdtTxtName, mEdtTxtUser,
                    mEdtTxtPass, mEdtTxtPassConfirm;
    private UserDAO userDAO;
    private LoginHelper mLoginHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userDAO = new UserDAO();
        mLoginHelper = new LoginHelper();

        mEdtTxtCpf = findViewById(R.id.edt_txt_cpf);
        mEdtTxtName = findViewById(R.id.edt_txt_name);
        mEdtTxtUser = findViewById(R.id.edt_txt_user);
        mEdtTxtPass = findViewById(R.id.edt_txt_password);
        mEdtTxtPassConfirm = findViewById(R.id.edt_txt_password_confirm);

        Button btnSignup = findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(register);
    }

    private View.OnClickListener register = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String cpf = mEdtTxtCpf.getText().toString().trim();
            String name = mEdtTxtName.getText().toString().trim();
            String username = mEdtTxtUser.getText().toString().trim();
            String pass = mEdtTxtPass.getText().toString();
            String passConfirm = mEdtTxtPassConfirm.getText().toString();

            //Validação dos campos em branco
            if(cpf.isEmpty() || name.isEmpty() || username.isEmpty() ||
                    pass.isEmpty() || passConfirm.isEmpty()){
                setToastMessage("Campos não podem estar em branco");
            } else {
                //Validação CPF
                if(!CpfUtil.isValid(cpf)){
                    setToastMessage("CPF inválido");

                //Verifica se Senhas Conferem
                } else if(!pass.contentEquals(passConfirm)){
                    setToastMessage("Senhas Não Conferem");
                } else {
                    User user = new User(cpf, name, username, HashHandler.hashedString(pass));
                    if(mLoginHelper.userExists(user)){
                        setToastMessage("Usuário já existe");
                    } else {
                        userDAO.salvar(user);
                        setToastMessage("Cadastro efetuado");
                        finish();
                    }
                }
            }

        }
    };

    private void setToastMessage(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
