package com.example.springexam.service;

import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.RegisterDTO;
import com.example.springexam.payload.SignInDTO;
import com.example.springexam.payload.TokenDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.UUID;

public interface AuthService extends UserDetailsService {
    ApiResult<?> signUp(RegisterDTO registerDTO);
    ApiResult<TokenDTO> signIn(SignInDTO signInDTO);

    //REFRESH TOKEN YO'LI YOZILSIN

    ApiResult<?> confirmAccount(Integer userId, String verificationCode);

}
