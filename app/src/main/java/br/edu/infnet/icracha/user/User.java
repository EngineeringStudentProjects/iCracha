package br.edu.infnet.icracha.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.icracha.report.AttendanceReport;

public class User implements Serializable {

    private User() {
        //dummyList();
    }

    public User(String cpf, String name, String username, String password) {
        this();
        this.cpf = cpf;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User(String cpf, String name, String username, String password, boolean status){
        this(cpf, name, username, password);
        this.status = status;
    }

    private String cpf;
    private String name;
    private String username;
    private String password;
    private boolean status = false;
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

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }

}
