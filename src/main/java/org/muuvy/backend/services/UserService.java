package org.muuvy.backend.services;

import org.muuvy.backend.dto.UserDto;
import org.muuvy.backend.models.UserModel;

public class UserService {

    UserDto userDto;
    UserModel userModel;

    public UserDto createUser(String id, String  name){
        userDto.

        return userDto ;
    }

    public void deleteById(){



    }

    public UserDto getUserDto(){

        return  userDto;
    }


    public void createUser(UserDto user) {
        userModel.setUserId(user);
        userModel.setUserFullName();
    }

    public void deleteById(String userId) {
    }
}
