package br.edu.infnet.icracha.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.icracha.report.AttendanceReport;

public class User implements Serializable {

    private User() {
    }

    public User(String cpf, String name, String username, String password) {
        this.cpf = cpf;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    private String cpf;
    private String name;
    private String username;
    private String password;
    private String birthday;
    private String phone;
    private String email;
    private boolean status = false;

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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
