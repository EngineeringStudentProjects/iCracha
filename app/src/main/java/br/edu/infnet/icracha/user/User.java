package br.edu.infnet.icracha.user;

import java.io.Serializable;

public class User implements Serializable {

    public User() {}

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

    //public String getPhone() { return phone; }
}
