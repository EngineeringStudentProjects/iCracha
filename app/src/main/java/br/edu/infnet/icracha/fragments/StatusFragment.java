package br.edu.infnet.icracha.fragments;


import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.icracha.DAO.ReportHourDAO;
import br.edu.infnet.icracha.R;
import br.edu.infnet.icracha.barcode.BarcodeCaptureActivity;
import br.edu.infnet.icracha.report.AttendanceReport;
import br.edu.infnet.icracha.report.ReportHour;
import br.edu.infnet.icracha.report.ReportHourAdapter;
import br.edu.infnet.icracha.util.PontoHelper;

import static br.edu.infnet.icracha.ManagerActivity.mReportDao;
import static br.edu.infnet.icracha.ManagerActivity.mReportList;
import static br.edu.infnet.icracha.ManagerActivity.mUserDao;
import static br.edu.infnet.icracha.ManagerActivity.user;


/***
 *
 * Subclasse de {@link Fragment} que trabalha o status do usuário dentro da empresa.
 * Exibe o status do usuário e permite que o mesmo realize uma leitura do QR Code.
 *
 * @author Pedro Lopes
 * @version 1.0.1
 *
 */
public class StatusFragment extends Fragment {

    private TextView mStatusTextView, mTxtNome, mTxtWarning, mTxtDataStatus;
    public ListView lvwReportTime;
    private Button mScanBarcodeButton;
    private BaseAdapter mAdapter;
    private List<ReportHour> reportHourList;
    private PontoHelper pontoHelper;

    private final String LOG_TAG = "BARCODE_SCAN";
    private final int BARCODE_READER_REQUEST_CODE = 1;
    private final String HASH_BATER_PONTO = "HashBaterPonto";

    private ReportHourDAO mReportHourDao;

    private LocationManager locationManager;


    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pontoHelper = new PontoHelper();

        mTxtWarning = getView().findViewById(R.id.txtWarning);
        lvwReportTime = getView().findViewById(R.id.lvw_report_time);
        mScanBarcodeButton = getView().findViewById(R.id.btnScan);

        mScanBarcodeButton.setOnClickListener(scanBarCode);
    }

    @Override
    public void onResume() {
        super.onResume();

        mTxtNome = getView().findViewById(R.id.txtNome);
        mStatusTextView = getView().findViewById(R.id.txtStatus);
        mTxtDataStatus = getView().findViewById(R.id.txt_date_status);

        mTxtNome.setText(user.getName());
        mStatusTextView.setText(getStatus());
        mTxtDataStatus.setText(pontoHelper.getDataFormatada());

        listarHorarioDia();

    }

    private View.OnClickListener scanBarCode = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //pegarLocalizacao();
            Intent intent = new Intent(v.getContext(), BarcodeCaptureActivity.class);
            startActivityForResult(intent, BARCODE_READER_REQUEST_CODE);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BARCODE_READER_REQUEST_CODE){
            if (resultCode == CommonStatusCodes.SUCCESS){
                if (data != null){
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    setStatusReport(barcode.displayValue);
                } else {
                    mTxtWarning.setText(R.string.no_barcode_captured);
                }
            } else {
                Log.e(String.valueOf(LOG_TAG), String.format(getString(R.string.barcode_error_format),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void setStatusReport(String status){

        if(status.equals(HASH_BATER_PONTO)){

            boolean newStatus = !user.getStatus();
            user.setStatus(newStatus);

            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            mUserDao.salvar(user);
                        }
                    }
            ).start();

            String data = pontoHelper.getDataAtual();
            String hora = pontoHelper.getHoraAtual();

            final AttendanceReport attendanceReport = new AttendanceReport(data, hora, newStatus);

            mReportList.add(attendanceReport);

            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            mReportDao.salvar(attendanceReport);
                        }
                    }
            ).start();
            mStatusTextView.setText(getStatus());
        }
    }

    private String getStatus(){

        if(user.getStatus()){
           return "Na Empresa";

        } else {
            return "Fora da Empresa";
        }
    }

    private void listarHorarioDia(){

        PontoHelper pontoHelper = new PontoHelper();

        String data = pontoHelper.getDataAtual();

        reportHourList = new ArrayList<>();

        mAdapter = new ReportHourAdapter(getContext(), reportHourList);

        mReportHourDao = new ReportHourDAO(user.getCpf(), data, mAdapter, reportHourList);

        lvwReportTime.setAdapter(mAdapter);

    }

}
