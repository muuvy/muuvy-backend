package org.muuvy.backend.services;

import org.muuvy.backend.dto.UserDto;
import org.muuvy.backend.models.UserModel;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {

    UserDto userDto;
    UserModel userModel;

    public UserDto createUser(String id, String  name){

        return userDto ;
    }

    public void deleteById(){



    }

    public UserDto getUserDto(){

        return  userDto;
    }


    public void createUser(UserDto user) {
        //userModel.setUserId(user);
        //userModel.setUserFullName();
    }

    public void deleteById(String userId) {
    }
}
