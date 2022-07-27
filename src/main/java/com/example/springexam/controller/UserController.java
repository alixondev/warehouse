package com.example.springexam.controller;

import com.example.springexam.payload.*;
import com.example.springexam.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(UserController.USER_CONTROLLER_PATH)
public interface UserController {

    String USER_CONTROLLER_PATH = AppConstant.BASE_PATH +"/user";

    @GetMapping("/get")
    ApiResult<List<UserInfoDTO>> getAll();

    @PostMapping("/add")
    ApiResult<UserDTO> add(@RequestBody UserAddDTO userAddDTO);

    @PutMapping("/{id}")
    ApiResult<UserUpdateDTO> update(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Integer id);

    @DeleteMapping("/{id}")
    String delete(@PathVariable Integer id);
}
