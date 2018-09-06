package br.edu.infnet.icracha.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.icracha.user.User;

public class AttendanceReport implements Serializable {

    private String date;
    private String cpf;
    //private List<ReportHour> mReportHourList;

    private AttendanceReport(){ }

    public AttendanceReport(String date, String cpf) {
        this();
        this.date = date;
        this.cpf = cpf;
    }

    /*public AttendanceReport(String date, String cpf, List<ReportHour> reportHourList) {
        this(date, cpf);
        this.mReportHourList = reportHourList;
    }*/

    public String getDate() {
        return date;
    }

    public String getUser() {
        return cpf;
    }

    /*public List<ReportHour> getmReportHourList() {
        return mReportHourList;
    }

    public void setmReportHour(ReportHour reportHour) {
        this.mReportHourList.add(reportHour);
    }*/
}
