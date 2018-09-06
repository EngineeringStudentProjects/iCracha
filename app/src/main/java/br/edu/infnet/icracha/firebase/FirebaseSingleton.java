package br.edu.infnet.icracha.firebase;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseSingleton {

    private static FirebaseSingleton instance;
    private static FirebaseDatabase database;

    private FirebaseSingleton(){
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
    }

    public static FirebaseSingleton getInstance(){
        if(instance == null){
            instance = new FirebaseSingleton();
        }

        return instance;
    }

    public static FirebaseDatabase getDatabase() {
        return database;
    }
}