package org.muuvy.backend.services;

import org.muuvy.backend.dto.UserDto;

public class UserService {

    UserDto userDto;

    public UserDto createUser(String id, String  name){

        UserDto userDto = new UserDto(id,name);

        return userDto ;
    }

    public void deleteById(){



    }

    public UserDto getUserDto(){

        return  userDto;
    }


    public void createUser(UserDto user) {



    }

    public void deleteById(String userId) {
    }
}
