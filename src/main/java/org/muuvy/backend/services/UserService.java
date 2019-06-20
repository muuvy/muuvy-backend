package org.muuvy.backend.services;

import org.muuvy.backend.dto.UserDto;
import org.muuvy.backend.models.UserModel;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {

    UserDto userDto;
    UserModel userModel;

    public UserDto createUser(UserDto user){


        return userDto ;
    }

    public void deleteById(String id){



    }

    public UserDto getUserDto(){

        return  userDto;
    }
}
