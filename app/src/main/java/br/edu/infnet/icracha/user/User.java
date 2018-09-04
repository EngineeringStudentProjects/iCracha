package br.edu.infnet.icracha.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.icracha.report.AttendanceReport;

public class User implements Serializable {

    private User() {
        dummyList();
    }

    public User(String cpf, String name, String username, String password) {
        this();
        this.cpf = cpf;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    private String cpf;
    private String name;
    private String username;
    private String password;
    private List<AttendanceReport> attendanceReportList;
    //private String phone;

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setAttendanceReportList(List<AttendanceReport> reportList){
        this.attendanceReportList = reportList;
    }

    public List<AttendanceReport> getAttendanceReportList() {

        return attendanceReportList;

    }

    private void dummyList(){
        List<AttendanceReport> lista = new ArrayList<>();

        for( int i = 1; i < 16; i++){
            lista.add(new AttendanceReport(
                    i,
                    04,
                    2018
            ));

        }

        attendanceReportList = lista;
    }

}
