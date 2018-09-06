package br.edu.infnet.icracha.report;

import java.io.Serializable;

public class ReportHour implements Serializable {

    public ReportHour(String hour, boolean status) {
        this.mHour = hour;
        this.mStatus = status;
    }

    private String mHour;
    private boolean mStatus;

    public String getHour() {
        return mHour;
    }

    public boolean getStatus(){
        return mStatus;
    }
}
