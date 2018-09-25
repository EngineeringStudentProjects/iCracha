package br.edu.infnet.icracha.util;

import java.util.List;

import br.edu.infnet.icracha.DAO.UserDAO;
import br.edu.infnet.icracha.user.User;
import br.edu.infnet.icracha.util.HashHandler;

public class LoginHelper {

    private UserDAO mUserDao;
    private User mUsers;

    public LoginHelper(String uid){
        mUserDao = new UserDAO(uid);
    }

    public User getUser(){

        return mUserDao.getUser();

        /*for(User user : mUsers){
            if(user.getUsername().equals(username)){
                if(HashHandler.hashedString(password).equals(user.getPassword())){
                    return user;
                }
            }
        }

        return null;*/
    }
/*
    public boolean userExists(User user){

        mUsers = mUserDao.listar();

        for(int i = 0; i < mUsers.size(); i++){
            if(mUsers.get(i).getCpf().equals(user.getCpf())){
                return true;
            } else if(mUsers.get(i).getUsername().equals(user.getUsername())){
                return true;
            }
        }

        return false;
    }*/

}
