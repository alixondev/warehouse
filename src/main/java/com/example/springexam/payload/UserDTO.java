package com.example.springexam.payload;

import lombok.Data;

import java.util.List;
@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String position;
    private String password;
    private boolean active;
    private List<Integer> wareHousesId;
}
