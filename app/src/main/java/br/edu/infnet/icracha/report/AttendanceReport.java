package br.edu.infnet.icracha.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.icracha.user.User;

public class AttendanceReport implements Serializable {

    private String date;
    private String mHour;
    private boolean mStatus;

    private AttendanceReport(){ }

    public AttendanceReport(String date) {
        this();
        this.date = date;
    }

    public AttendanceReport(String date, String hour, boolean status) {
        this(date);
        this.mHour = hour;
        this.mStatus = status;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return mHour;
    }

    public boolean getStatus(){
        return mStatus;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String mHour) {
        this.mHour = mHour;
    }

    public void setStatus(boolean mStatus) {
        this.mStatus = mStatus;
    }
}
