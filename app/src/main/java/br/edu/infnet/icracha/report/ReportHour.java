package br.edu.infnet.icracha.report;

import java.io.Serializable;

public class ReportHour implements Serializable {

    public ReportHour(String hour, String status) {
        this.mHour = hour;
        this.mStatus = status;
    }

    private String mHour;
    private String mStatus;

    public String getHour() {
        return mHour;
    }

    public String getStatus(){
        return mStatus;
    }
}
