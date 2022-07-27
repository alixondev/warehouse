package com.example.springexam.service;

import com.example.springexam.entity.User;
import com.example.springexam.entity.Warehouse;
import com.example.springexam.exception.RestException;
import com.example.springexam.payload.*;
import com.example.springexam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public ApiResult<List<UserInfoDTO>> getAll() {

        List<User> userList = userRepository.findAll();
        List<UserInfoDTO> workerInfoList = userList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());

        return ApiResult.successResponse(workerInfoList);
    }



    @Override
    public ApiResult<UserDTO> add(UserAddDTO userAddDTO) {
        Warehouse warehouse = new Warehouse();

        User user = new User(
                userAddDTO.getId(),
                userAddDTO.getFirstName(),
                userAddDTO.getLastName(),
                userAddDTO.getPhoneNumber(),
                generateCode(),
                userAddDTO.getPassword(),
                userAddDTO.isActive(),
                warehouse.getName()


        );
        userRepository.save(user);
        return ApiResult.successResponse(user,true,"success");
    }

    @Override
    public ApiResult<UserUpdateDTO> update(UserUpdateDTO userUpdateDTO, Integer id) {
        checkExistingName(userUpdateDTO.getFirstName(),id);
        User user = getByIdOrElseThrow(id);

            user.setFirstName(userUpdateDTO.getFirstName());
            user.setLastName(userUpdateDTO.getLastName());
            user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
            user.setActive(userUpdateDTO.isActive());

            userRepository.save(user);

        return ApiResult.successResponse(user,true,"success");
    }

    @Override
    public String delete(Integer id) {
        User user = getByIdOrElseThrow(id);
            userRepository.delete(user);
        return "success";
    }


    private UserInfoDTO entityToInfoDTO(User user) {
            return new UserInfoDTO(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPhoneNumber(),
                    (Set<Warehouse>) user.getWareHouse()
            );


    }

    private static String generateCode(){
        double random=Math.random()*1000;
        return String.valueOf((random-random%1));
    }

    public User getByIdOrElseThrow(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> RestException.notFound("User"));
    }

    private void checkExistingName(String name, Integer id) {
        boolean exists = userRepository.existsByFirstNameAndIdNot(name,id);
        if (exists) throw RestException.alreadyExist("User");
        Optional<User> optionalWorker = userRepository.findById(id);
        if (optionalWorker.isEmpty()) throw RestException.notFound("User");
    }

    private void checkExistingName(String name) {
        boolean exist = userRepository.existsByFirstName(name);
        if (exist) throw RestException.alreadyExist("Category");
    }
}
