package com.example.demo.Services;

import com.example.demo.DTOs.RequestDto.UserDto;
import com.example.demo.DTOs.ResponseDto.UserResponseDto;
import com.example.demo.Exceptions.UserNotFound;
import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserDto userDto){

        User user = UserTransformer.convertDtoToEntity(userDto);
        userRepository.save(user);

        return "User has been added successfully ";
    }

    public UserResponseDto getOldestUser()throws UserNotFound {
        //Prevent you from exposing the PK
        //Prevents Infinite recursion incase it occurs
        List<User> users = userRepository.findAll();
        Integer maxAge = 0;

        User userAns = null;

        for(User user: users)
        {
            if(user.getAge()>maxAge){
                maxAge = user.getAge();
                userAns = user;
            }
        }

        if(userAns==null){
            throw new UserNotFound("No user Found");
        }

        //We need to transform the UserEntity to the userResponse
        UserResponseDto userResponseDto = UserTransformer.convertEntityToDto(userAns);

        return userResponseDto;
    }

    public List<User> getAllUserGreaterThan(Integer age){

        List<User> users = userRepository.findUserWithAgeGreater(age);
        return users;

    }

}