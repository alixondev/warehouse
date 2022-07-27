package com.example.springexam.payload;

import com.example.springexam.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateDTO {

    private String firstName;
    private String lastName;

    private String phoneNumber;

    private String WorkerId;

    private String password;

    private Warehouse warehouse;

    private boolean active;

    private String position;

}
