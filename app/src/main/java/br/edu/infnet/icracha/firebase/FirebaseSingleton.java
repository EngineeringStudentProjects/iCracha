package br.edu.infnet.icracha.firebase;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class FirebaseSingleton {

    private static FirebaseSingleton instance;
    private static FirebaseDatabase database;
    private static FirebaseStorage storage;

    private FirebaseSingleton(){
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        storage = FirebaseStorage.getInstance();
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

    public static FirebaseStorage getStorage() { return  storage; }
}