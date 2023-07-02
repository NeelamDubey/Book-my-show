package com.example.demo.Transformers;


import com.example.demo.DTOs.RequestDto.UserDto;
import com.example.demo.DTOs.ResponseDto.UserResponseDto;
import com.example.demo.Models.User;

public class UserTransformer {

    public static User convertDtoToEntity(UserDto userDto){

        User userObj = User.builder().age(userDto.getAge()).email(userDto.getEmailId()).mobNo(userDto.getMobNo())
                .name(userDto.getName()).build();
        return userObj;
    }

    public static UserResponseDto convertEntityToDto(User user){

        UserResponseDto userResponseDto = UserResponseDto.builder().age(user.getAge()).name(user.getName())
                .mobNo(user.getMobNo()).build();
        return userResponseDto;
    }


}