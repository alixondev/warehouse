package com.example.springexam.payload;

import com.example.springexam.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoDTO {

    private String firstName;
    private String lastName;

    private String phoneNumber;

    private String WorkerId;

    private String password;

    private Warehouse warehouse;

    private boolean active;

    private String position;

    public UserInfoDTO(String firstName, String lastName, String phoneNumber, Set<Warehouse> wareHouse) {


    }
}
