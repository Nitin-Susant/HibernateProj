package org.hibernat.Service;

import org.hibernat.Entity.Users;
import org.hibernat.Repositary.UserRepo;

import java.util.List;

public class UserService {
    UserRepo userRepo = null;

    public UserService(){
        userRepo = new UserRepo();
    }

    public void addUser(Users users){
        userRepo.saveUser(users);

    }

    public void updateUser(Integer id,Users newUser){
        Users user = getUser(id);
        user.setName(newUser.getName());
        userRepo.updateUser(user);


    }

    public Users getUser( Integer id){
        Users userById = userRepo.getUserById(id);
        return userById ;
    }
    public List<Users> getAllUser(){
        List<Users> users = userRepo.getUsers();
        return  users;
    }
    public boolean removeUser(Integer id){
        Users user = getUser(id);
        boolean flag = false;
        if (user!=null) {
            userRepo.removeUser(user);
            flag = true;
        }
        else
            flag= false;
        return flag;
    }

    public void removeAllUser(){
        userRepo.removeAllUser();

    }



}
