package br.edu.infnet.icracha.DAO;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.icracha.firebase.FirebaseSingleton;
import br.edu.infnet.icracha.user.User;

public class UserDAO {

    private DatabaseReference mDatabaseRef;
    private List<User> mUsers = new ArrayList<>();
    private User mUser;
    private String mUID;

    public UserDAO() {
        mDatabaseRef = FirebaseSingleton.getInstance().getDatabase().getReference("users");
        //mDatabaseRef.addChildEventListener(carregar);
    }

    public UserDAO(String uid) {
        mDatabaseRef = FirebaseSingleton.getInstance().getDatabase().getReference("users");
        mDatabaseRef.addChildEventListener(carregar);
        this.mUID = uid;
    }

    private ChildEventListener carregar = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            mUser = dataSnapshot.getValue(User.class);
            mUsers.add(mUser);

            for(User user : mUsers){
                if(user.getuId() == mUID){
                    mUser = user;
                    break;
                }
            }

        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            mUser = dataSnapshot.getValue(User.class);

            /*for(int i = 0; i < mUsers.size(); i++){
                if(mUsers.get(i).getCpf().equals(user.getCpf())){
                    mUsers.set(i, user);
                    break;
                }
            }*/
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            /*User user = dataSnapshot.getValue(User.class);

            for(int i = 0; i < mUsers.size(); i++){
                if(mUsers.get(i).getCpf().equals(user.getCpf())){
                    mUsers.remove(user);
                    break;
                }
            }*/
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public void salvar(User user){
        mDatabaseRef.child(user.getuId()).setValue(user);
    }

    public User getUser(){ return mUser; }

    public List<User> listar(){
        return mUsers;
    }



}
