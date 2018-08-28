package br.edu.infnet.icracha.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import br.edu.infnet.icracha.R;
import br.edu.infnet.icracha.barcode.BarcodeCaptureActivity;


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

    private TextView mResultTextView;
    private Button mScanBarcodeButton;
    private final String LOG_TAG = "BARCODE_SCAN";
    private final int BARCODE_READER_REQUEST_CODE = 1;

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

        mResultTextView = getView().findViewById(R.id.txtStatus);
        mScanBarcodeButton = getView().findViewById(R.id.btnScan);

        mScanBarcodeButton.setOnClickListener(scanBarCode);
    }

    private View.OnClickListener scanBarCode = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
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
                    mResultTextView.setText(barcode.displayValue);
                } else {
                    mResultTextView.setText(R.string.no_barcode_captured);
                }
            } else {
                Log.e(String.valueOf(LOG_TAG), String.format(getString(R.string.barcode_error_format),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
