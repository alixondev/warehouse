package com.example.springexam.controller;

import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.RegisterDTO;
import com.example.springexam.payload.SignInDTO;
import com.example.springexam.payload.TokenDTO;
import com.example.springexam.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public ApiResult<?> signUp(RegisterDTO registerDTO) {
        return authService.signUp(registerDTO);
    }

    @Override
    public ApiResult<TokenDTO> signIn(SignInDTO signInDTO) {
        return authService.signIn(signInDTO);
    }

    @Override
    public ApiResult<?> confirmAccount(Integer userId, String verificationCode) {
        return authService.confirmAccount(userId,verificationCode);
    }
}
