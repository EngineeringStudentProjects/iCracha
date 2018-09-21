package br.edu.infnet.icracha.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import br.edu.infnet.icracha.ManagerActivity;
import br.edu.infnet.icracha.R;
import br.edu.infnet.icracha.user.User;
import br.edu.infnet.icracha.util.CpfUtil;
import br.edu.infnet.icracha.util.HashHandler;

import static br.edu.infnet.icracha.ManagerActivity.mUserDao;
import static br.edu.infnet.icracha.ManagerActivity.user;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileEditFragment extends Fragment {

    private TextView mTxtCpf, mTxtUsername;
    private EditText mEdtName, mEdtBirthday,
            mEdtPhone, mEdtEmail, mEdtPass, mEdtPassConf;

    //private ImageView mImgProfile;

    private FloatingActionButton mBtnCancel;
    private FloatingActionButton mBtnSave;

    public ProfileEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_edit, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTxtCpf = getView().findViewById(R.id.txtCpf);
        mTxtUsername = getView().findViewById(R.id.txtUsername);
        mEdtName = getView().findViewById(R.id.edtName);
        mEdtBirthday = getView().findViewById(R.id.edtBirthday);
        mEdtPhone = getView().findViewById(R.id.edtPhone);
        mEdtEmail = getView().findViewById(R.id.edtEmail);
        mEdtPass = getView().findViewById(R.id.edt_password);
        mEdtPassConf = getView().findViewById(R.id.edt_password_confirm);

        //mImgProfile = getView().findViewById(R.id.imgProfile);

        mTxtCpf.setText(user.getCpf());
        mTxtUsername.setText(user.getUsername());
        mEdtName.setText(user.getName());
        mEdtBirthday.setText(user.getBirthday());
        mEdtPhone.setText(user.getPhone());
        mEdtEmail.setText(user.getEmail());
    }

    @Override
    public void onResume() {
        super.onResume();

        mBtnCancel = getView().findViewById(R.id.btnCancel);
        mBtnSave = getView().findViewById(R.id.btnSave);

        mBtnCancel.setOnClickListener(cancelEdit);
        mBtnSave.setOnClickListener(saveEdit);

    }

    private View.OnClickListener cancelEdit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((ManagerActivity)getActivity()).goToFragment(new ProfileFragment());
        }
    };

    private View.OnClickListener saveEdit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String name = mEdtName.getText().toString().trim();
            String birthday = mEdtBirthday.getText().toString().trim();
            String phone = mEdtPhone.getText().toString().trim();
            String email = mEdtEmail.getText().toString().trim();
            String pass = mEdtPass.getText().toString();
            String passConfirm = mEdtPassConf.getText().toString();

            //Validação dos campos em branco
            if(name.isEmpty()){
                setToastMessage("Campo Nome não pode estar em branco");
            } else {
                //Validação Senha
                if(!pass.isEmpty()){
                    if(passConfirm.isEmpty()){
                        setToastMessage("Confirme sua senha");
                    } else {
                        //Verifica se Senhas Conferem
                        if(!pass.contentEquals(passConfirm)){
                            setToastMessage("Senhas Não Conferem");
                        } else {
                            user.setPassword(HashHandler.hashedString(pass));
                        }
                    }
                } else {

                    user.setName(name);
                    user.setBirthday(birthday);
                    user.setPhone(phone);
                    user.setEmail(email);

                    mUserDao.salvar(user);

                    setToastMessage("Cadastro atualizado");
                    ((ManagerActivity)getActivity()).goToFragment(new ProfileFragment());
                }
            }
        }
    };

    private void setToastMessage(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
