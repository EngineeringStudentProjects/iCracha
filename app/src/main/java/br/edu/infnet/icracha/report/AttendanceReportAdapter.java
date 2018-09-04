package br.edu.infnet.icracha.report;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.icracha.R;

public class AttendanceReportAdapter extends RecyclerView.Adapter {

    private List<AttendanceReport> mAttendanceReports;

    private ListView mLvwReportTime;
    private List<ReportHour> reportHourList;
    private BaseAdapter mAdapter;

    private Context contexto;

    public AttendanceReportAdapter(List<AttendanceReport> attendanceReportList){
        this.mAttendanceReports = attendanceReportList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        contexto = parent.getContext();
        reportHourList = new ArrayList<>();
        mAdapter = new ReportHourAdapter(contexto, reportHourList);

        View card = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.attendance_report_card, parent, false);

        return new AttendanceReportViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AttendanceReport attendanceReport = mAttendanceReports.get(position);

        AttendanceReportViewHolder viewHolder =(AttendanceReportViewHolder) holder;

        viewHolder.reportDay.setText(attendanceReport.getDay() + "");
        viewHolder.reportMonth.setText(attendanceReport.getMonth() + "");
        viewHolder.reportYear.setText(attendanceReport.getYear() + "");

        mLvwReportTime = viewHolder.lvwReportTime;
        mAdapter = new ReportHourAdapter(contexto, attendanceReport.getmReportHourList());
        mLvwReportTime.setAdapter(mAdapter);

    }

    @Override
    public int getItemCount() {
        return mAttendanceReports.size();
    }
}
