package com.example.springexam.controller;

import com.example.springexam.payload.*;
import com.example.springexam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{

    private final UserService userService;

    @Override
    public ApiResult<List<UserInfoDTO>> getAll() {
        return userService.getAll();
    }

    @Override
    public ApiResult<UserDTO> add(UserAddDTO userAddDTO) {
        return userService.add(userAddDTO);
    }

    @Override
    public ApiResult<UserUpdateDTO> update(UserUpdateDTO userUpdateDTO, Integer id) {
        return userService.update(userUpdateDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return userService.delete(id);
    }
}
