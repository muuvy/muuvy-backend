package org.muuvy.backend.dto;

public class UserDto {
    private String id;
    private String fullName;

    public UserDto(String id, String fullName){
        this.id = id;
        this.fullName= fullName;
    }
}
