package br.edu.infnet.icracha.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.infnet.icracha.ManagerActivity;
import br.edu.infnet.icracha.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

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
