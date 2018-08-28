package br.edu.infnet.icracha.util;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashHandler {

    private HashHandler(){}

    private static final String LOG_TAG = "HASH_HANDLE";

    public static String hashedString(String string){
        String hashedPass = "";

        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(string.getBytes());
            byte[] hashBytes = messageDigest.digest();

            //Hash da senha
            hashedPass = new String(hashBytes);
        } catch (NoSuchAlgorithmException ex){
            Log.e(LOG_TAG, ex.getMessage());
        }

        return hashedPass;
    }

}