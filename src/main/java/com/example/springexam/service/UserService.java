package com.example.springexam.service;

import com.example.springexam.payload.*;

import java.util.List;

public interface UserService {

    ApiResult<List<UserInfoDTO>> getAll();

    ApiResult<UserDTO> add(UserAddDTO userAddDTO);

    ApiResult<UserUpdateDTO> update(UserUpdateDTO userUpdateDTO, Integer id);

    String delete(Integer id);
}
