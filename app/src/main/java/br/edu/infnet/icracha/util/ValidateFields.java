package br.edu.infnet.icracha.util;

import java.util.regex.Pattern;

public class ValidateFields {

    private static final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private static final String namePattern = "[A-Za-z ]*";
    //^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$/i

    public static boolean validateEmail(String email){
        Pattern pattern = Pattern.compile(emailPattern);
        return pattern.matcher(email).matches();

    }

    public static boolean validateName(String name){
        Pattern pattern = Pattern.compile(namePattern);
        return pattern.matcher(name).matches();

    }

}
