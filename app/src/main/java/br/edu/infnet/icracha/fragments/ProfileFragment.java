package br.edu.infnet.icracha.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.edu.infnet.icracha.ManagerActivity;
import br.edu.infnet.icracha.R;

import static br.edu.infnet.icracha.ManagerActivity.user;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView mTxtCpf, mTxtName, mTxtUsername,
            mTxtBirthday, mTxtPhone, mTxtEmail;

    private FloatingActionButton mBtnEdit;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTxtCpf = getView().findViewById(R.id.txtCpf);
        mTxtUsername = getView().findViewById(R.id.txtUsername);
        mTxtName = getView().findViewById(R.id.txtName);
        mTxtBirthday = getView().findViewById(R.id.txtBirthday);
        mTxtPhone = getView().findViewById(R.id.txtPhone);
        mTxtEmail = getView().findViewById(R.id.txtEmail);

        mTxtCpf.setText(user.getCpf());
        mTxtUsername.setText(user.getUsername());
        mTxtName.setText(user.getName());
        mTxtBirthday.setText(user.getBirthday());
        mTxtPhone.setText(user.getPhone());
        mTxtEmail.setText(user.getEmail());
    }

    @Override
    public void onResume() {
        super.onResume();

        mBtnEdit = getView().findViewById(R.id.btnEdit);

        mBtnEdit.setOnClickListener(editProfile);

    }

    private View.OnClickListener editProfile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ((ManagerActivity)getActivity()).goToFragment(new ProfileEditFragment());

        }
    };
}
