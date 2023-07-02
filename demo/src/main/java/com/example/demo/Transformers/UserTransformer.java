package com.example.demo.Transformers;

import com.example.demo.DTOs.RequestDto.UserDto;
import com.example.demo.DTOs.ResponseDto.UserResponseDto;
import com.example.demo.Models.User;

public class UserTransformer {
    public static User UserDtoToEntity(UserDto userDto) {
        User user = User.builder().age(userDto.getAge())
                .emailId(userDto.getEmailId()).mobileNo(userDto.getMobileNo())
                .name(userDto.getName()).build();

        return user;
    }
    public static UserResponseDto UserDtoToEntity(User user){

        UserResponseDto userResponseDto = UserDto.builder().age(user.getAge()).name(user.getName())
                .mobileNo(user.getMobNo()).build();
        return userResponseDto;
    }
}
