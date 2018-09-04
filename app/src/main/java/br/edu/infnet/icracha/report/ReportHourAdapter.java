package br.edu.infnet.icracha.report;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.edu.infnet.icracha.R;

public class ReportHourAdapter extends ArrayAdapter<ReportHour> {

    private Context contexto;

    public ReportHourAdapter(@NonNull Context context, List<ReportHour> reportHours) {
        super(context, R.layout.list_view_hour_report, reportHours);
        this.contexto = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ReportHour reportHour = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(contexto).inflate(R.layout.list_view_hour_report,
                    parent, false);
        }

        TextView txtReportHour = convertView.findViewById(R.id.txt_report_hour);
        TextView txtReportStatus = convertView.findViewById(R.id.txt_report_status);

        txtReportHour.setText(reportHour.getHour());
        txtReportStatus.setText(reportHour.getStatus());

        return convertView;

    }
}
