package br.edu.infnet.icracha.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.edu.infnet.icracha.R;
import br.edu.infnet.icracha.report.AttendanceReportAdapter;

import static br.edu.infnet.icracha.ManagerActivity.user;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceReportFragment extends Fragment {

    private RecyclerView attendaceList;

    private TextView mResultTextView, mTxtNome;

    public AttendanceReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attendance_report, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        mTxtNome = getView().findViewById(R.id.txtNome);
        mTxtNome.setText(user.getName());

        attendaceList = getActivity().findViewById(R.id.attendance_report_list);
        AttendanceReportAdapter adapter = new AttendanceReportAdapter(user.getAttendanceReportList());

        attendaceList.setLayoutManager(new LinearLayoutManager(getContext()));

        attendaceList.setAdapter(adapter);
    }
}
