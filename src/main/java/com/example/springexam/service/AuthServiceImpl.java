package com.example.springexam.service;


import com.example.springexam.controller.AuthController;
import com.example.springexam.entity.Role;
import com.example.springexam.entity.User;
import com.example.springexam.enums.RoleEnum;
import com.example.springexam.exception.RestException;
import com.example.springexam.payload.*;
import com.example.springexam.repository.RoleRepository;
import com.example.springexam.repository.UserRepository;
import com.example.springexam.security.JWTProvider;
import com.example.springexam.utils.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
//    private final TwilioService twilioService;

    @Value("${spring.mail.username}")
    private String myEmail;


    @Override
    public ApiResult<?> signUp(RegisterDTO registerDTO) {

        checkPhoneNUmber(registerDTO.getPhoneNumber());

        checkPasswordAndPrepasword(registerDTO.getPassword(), registerDTO.getPrePassword());

        Role roleEnum = roleRepository.findByName(AppConstant.USER_ROLE).orElseThrow(() -> RestException.notFound("RoleEnum"));

        User user = new User(
                registerDTO.getFirstName(),
                registerDTO.getLastName(),
                registerDTO.getPhoneNumber(),
                registerDTO.getEmail(),
                passwordEncoder.encode(registerDTO.getPassword()),
                roleEnum,
                generateVerificationCode()
        );

        userRepository.save(user);

        sendMail(user);
//        sendPhoneNumber(user);

        return ApiResult.successResponse("Successfully registered. Please confirm by email");
    }

//    private void sendPhoneNumber(User user) {
//        twilioService.sendVerificationCode(
//                user.getPhoneNumber(),
//                user.getVerificationCode()
//        );
//    }

    @Override
    public ApiResult<TokenDTO> signIn(SignInDTO signInDTO) {

        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInDTO.getUsername(),
                            signInDTO.getPassword()
                    )
            );
            String accessToken = jwtProvider.generateAccessToken(signInDTO.getUsername());
            String refreshToken = jwtProvider.generateRefreshToken(signInDTO.getUsername());
            TokenDTO tokenDTO = new TokenDTO(accessToken, refreshToken);
            return ApiResult.successResponse(tokenDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.restThrow("password or username is wrong");
        }
    }

    @Override
    public ApiResult<?> confirmAccount(Integer userId, String verificationCode) {

        User user = getUserByIdOrElseThrow(userId);

        if (!Objects.equals(verificationCode, user.getVerificationCode()))
            throw RestException.restThrow("wrong code");

        user.setEnabled(true);
        user.setVerificationCode(null);
        userRepository.save(user);

        return ApiResult.successResponse("Successfully confirmed");
    }

    private User getUserByIdOrElseThrow(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> RestException.notFound("User"));
    }

    private void checkPasswordAndPrepasword(String password, String prePassword) {
        if (!Objects.equals(password, prePassword))
            throw RestException.restThrow("password and prepasword not equal");
    }

    private void checkPhoneNUmber(String phoneNumber) {
        boolean exist = userRepository.existsByPhoneNumber(phoneNumber);
        if (exist) throw RestException.alreadyExist("User");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(username).orElseThrow(() -> RestException.notFound("User"));
    }


    private String generateVerificationCode() {
        String code = String.valueOf((int) (Math.random() * 1_000_000_000));
        return code.substring(0, 6);
    }

    private void sendMail(User user) {
        String url = "http://localhost" + AuthController.AUTHCONTROLLER_PATH + AuthController.CONFIRM_ACCOUNT +
                "?userId=" + user.getId() + "&verificationCode=" + user.getVerificationCode();
        String text = "Account confirmation. Please click this link!\n" + url;
        SendMessageDTO sendMessageDTO = new SendMessageDTO(
                user.getEmail(),
                myEmail,
                AppConstant.CONFIRMATION,
                text
        );

        mailService.sendMessage(sendMessageDTO);
    }


}
