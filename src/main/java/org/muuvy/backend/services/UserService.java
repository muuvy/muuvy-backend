package org.muuvy.backend.services;

import org.muuvy.backend.dto.UserDto;

import javax.inject.Inject;

public class UserService {

    @Inject
    UserDto userDto;

    public UserDto createUser(UserDto user){



        return userDto ;
    }

    public void deleteById(String id){



    }

    public UserDto getUserDto(){

        return  userDto;
    }





}
