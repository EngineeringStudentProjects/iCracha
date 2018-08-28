package br.edu.infnet.icracha.DAO;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.icracha.user.User;

public class UserDAO {

    private DatabaseReference mDatabaseRef;
    private List<User> mUsers = new ArrayList<>();

    public UserDAO() {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("users");
        mDatabaseRef.addChildEventListener(carregar);
    }

    private ChildEventListener carregar = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            User user = dataSnapshot.getValue(User.class);
            mUsers.add(user);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            User user = dataSnapshot.getValue(User.class);

            for(int i = 0; i < mUsers.size(); i++){
                if(mUsers.get(i).getCpf().equals(user.getCpf())){
                    mUsers.set(i, user);
                    break;
                }
            }
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            User user = dataSnapshot.getValue(User.class);

            for(int i = 0; i < mUsers.size(); i++){
                if(mUsers.get(i).getCpf().equals(user.getCpf())){
                    mUsers.remove(user);
                    break;
                }
            }
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public void salvar(User user){
        mDatabaseRef.child(user.getCpf()).setValue(user);
    }

    public List<User> listar(){
        return mUsers;
    }

    public void excluir(String cpf){
        mDatabaseRef.child(cpf).removeValue();
    }



}
