package br.edu.infnet.icracha.report;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import br.edu.infnet.icracha.R;

public class AttendanceReportViewHolder extends RecyclerView.ViewHolder {

    public TextView reportDay, reportMonth, reportYear;
    public ListView lvwReportTime;

    public AttendanceReportViewHolder(View itemView) {
        super(itemView);

        reportDay = itemView.findViewById(R.id.report_day);
        reportMonth = itemView.findViewById(R.id.report_month);
        reportYear = itemView.findViewById(R.id.report_year);
        lvwReportTime = itemView.findViewById(R.id.lvw_report_time);

    }
}
