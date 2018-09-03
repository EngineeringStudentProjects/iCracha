package br.edu.infnet.icracha.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import br.edu.infnet.icracha.ManagerActivity;
import br.edu.infnet.icracha.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileEditFragment extends Fragment {

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
            Toast.makeText(getContext(), "Salvando...", Toast.LENGTH_SHORT).show();
        }
    };
}
