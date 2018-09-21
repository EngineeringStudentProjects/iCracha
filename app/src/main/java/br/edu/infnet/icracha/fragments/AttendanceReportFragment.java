package br.edu.infnet.icracha.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.icracha.R;
import br.edu.infnet.icracha.report.AttendanceReport;
import br.edu.infnet.icracha.report.AttendanceReportAdapter;
import br.edu.infnet.icracha.report.ReportComplete;
import br.edu.infnet.icracha.report.ReportHour;

import static br.edu.infnet.icracha.ManagerActivity.mReportList;
import static br.edu.infnet.icracha.ManagerActivity.user;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceReportFragment extends Fragment {

    private RecyclerView attendaceList;

    private TextView mStatusTextView, mTxtNome;

    private List<ReportComplete> mReportComplete = new ArrayList<>();

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

        setReportCompleteList();

        mTxtNome = getView().findViewById(R.id.txtNome);
        mStatusTextView = getView().findViewById(R.id.txtStatus);

        mTxtNome.setText(user.getName());
        mStatusTextView.setText(getStatus());

        attendaceList = getActivity().findViewById(R.id.attendance_report_list);
        //AttendanceReportAdapter adapter = new AttendanceReportAdapter(mReportList);
        AttendanceReportAdapter adapter = new AttendanceReportAdapter(mReportComplete);

        attendaceList.setLayoutManager(new LinearLayoutManager(getContext()));

        attendaceList.setAdapter(adapter);
    }


    //MONTA LISTA DO RELATÓRIO COM FORMATO PARA SER UTILIZADO PELO ADAPTER
    //NÃO CONSEGUI RESOLVER O PROBLEMA DOS ERROS AO TENTAR CADASTRAR E RESGATAR VALORES DO
    //FIREBASE COM A CLASSE REPORTCOMPLETE(), POR ISSO OPTEI POR UTILIZAR UMA CLASSE APENAS PARA
    //ESSE FIM, A ATTENDENCEREPORT()
    private void setReportCompleteList(){

        for(AttendanceReport report : mReportList){

            ReportComplete reportComplete = new ReportComplete();

            reportComplete.setData(report.getDate());
            reportComplete.setReportHour(new ReportHour(report.getHour(), report.getStatus()));

            if(mReportComplete.isEmpty()){
                mReportComplete.add(reportComplete);
            } else {

                boolean naoContem = true;

                for(int i = 0; i < mReportComplete.size(); i++){

                    Log.i("TESTE", mReportComplete.get(i).getData() + " : " + reportComplete.getData());
                    if(mReportComplete.get(i).getData().equals(reportComplete.getData())) {
                        for (ReportHour reportHour : reportComplete.getReportHourList()) {
                            mReportComplete.get(i).setReportHour(reportHour);
                        }
                        naoContem = false;
                    }

                }

                if(naoContem){
                    mReportComplete.add(reportComplete);
                }

            }

        }

    }

    private String getStatus(){

        if(user.getStatus()){
            return "Na Empresa";

        } else {
            return "Fora da Empresa";
        }
    }
}
