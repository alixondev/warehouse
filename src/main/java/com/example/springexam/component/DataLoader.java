package com.example.springexam.component;

import com.example.springexam.entity.Role;
import com.example.springexam.entity.User;
import com.example.springexam.enums.PermissionEnum;

import com.example.springexam.repository.RoleRepository;
import com.example.springexam.repository.UserRepository;
import com.example.springexam.utils.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.List;

import static com.example.springexam.utils.AppConstant.ADMIN_ROLE;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {

        if (mode.equals("always")) {
            createUserRoler();
            createSuperUser();
        }

    }

    private void createSuperUser() {
        Role adminRole = new Role(
                ADMIN_ROLE,
                ADMIN_ROLE,
                List.of(PermissionEnum.values())
        );
        roleRepository.save(adminRole);

        User adminUser = new User(
                "alixon",
                "admin",
                "+998997295405",
                "alixondev.@gmail.com",
                passwordEncoder.encode("admin123"),
                adminRole,
                null
        );
        adminUser.setEnabled(true);
        userRepository.save(adminUser);
    }

    private void createUserRoler() {
        Role role = new Role(
                AppConstant.USER_ROLE,
                "user role",
                List.of(PermissionEnum.VIEW_ARTICLE)
        );
        roleRepository.save(role);
    }
}
