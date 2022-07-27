package com.example.springexam.controller;

import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.RegisterDTO;
import com.example.springexam.payload.SignInDTO;
import com.example.springexam.payload.TokenDTO;
import com.example.springexam.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

@RequestMapping(AuthController.AUTHCONTROLLER_PATH)
public interface AuthController {
    String AUTHCONTROLLER_PATH = AppConstant.BASE_PATH + "/auth/";
    String SIGN_UP = "sign-up";
    String SIGN_IN = "sign-in";
    String CONFIRM_ACCOUNT = "confirm-account";

    @PostMapping(SIGN_UP)
    ApiResult<?> signUp(@RequestBody RegisterDTO registerDTO);

    @PostMapping(SIGN_IN)
    ApiResult<TokenDTO> signIn(@RequestBody SignInDTO signInDTO);

    @GetMapping(CONFIRM_ACCOUNT)
    ApiResult<?> confirmAccount(@RequestParam Integer userId,
                                @RequestParam String verificationCode);


}
