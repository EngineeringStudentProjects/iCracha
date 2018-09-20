package br.edu.infnet.icracha.report;

import java.util.ArrayList;
import java.util.List;

public class ReportComplete {

    public ReportComplete() {
        this.reportHourList = new ArrayList<>();
    }

    private String data;
    private List<ReportHour> reportHourList;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<ReportHour> getReportHourList() {
        return reportHourList;
    }

    public void setReportHour(ReportHour reportHour) {
        this.reportHourList.add(reportHour);
    }
}
