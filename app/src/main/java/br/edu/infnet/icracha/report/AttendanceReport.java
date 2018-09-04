package br.edu.infnet.icracha.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AttendanceReport implements Serializable {

    private int mDay;
    private int mMonth;
    private int mYear;
    private List<ReportHour> mReportHourList;

    private AttendanceReport(){
        dummyList();
    }

    public AttendanceReport(int day, int month, int year) {
        this();
        this.mDay = day;
        this.mMonth = month;
        this.mYear = year;
    }

    public int getDay() {
        return mDay;
    }

    public int getMonth() {
        return mMonth;
    }

    public int getYear() {
        return mYear;
    }

    public List<ReportHour> getmReportHourList() {
        return mReportHourList;
    }

    public void setmReportHourList(List<ReportHour> mReportHourList) {
        this.mReportHourList = mReportHourList;
    }

    private void dummyList(){
        List<ReportHour> lista = new ArrayList<>();

        boolean status = true;

        for( int i = 1; i < 5; i++){
            lista.add(new ReportHour(
                    (7 + (i * 2)) + ":" + (i * 10),
                    status ? "Entrada" : "saída"
            ));

            status = !status;
        }

        mReportHourList = lista;
    }
}
