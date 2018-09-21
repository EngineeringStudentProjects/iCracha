package br.edu.infnet.icracha.DAO;

import com.google.firebase.storage.StorageReference;

import br.edu.infnet.icracha.firebase.FirebaseSingleton;

public class StorageDAO {

    private StorageReference mStorageRef;

    public StorageDAO() {
        mStorageRef = FirebaseSingleton.getInstance().getStorage().getReference();
    }
}
