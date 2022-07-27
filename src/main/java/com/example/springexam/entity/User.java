package com.example.springexam.entity;

import com.example.springexam.entity.Abs.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="user")
@SQLDelete(sql = "update user set deleted = true where id=?")
@Where(clause = "deleted=false")
public class User extends AbsLongEntity implements UserDetails {



    private String firstName;
    private String lastName;

    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Role role;

    private String password;

    private boolean active;
    private String verificationCode;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse wareHouse;

//    public User(Integer id, String firstName, String lastName, String phoneNumber, String position, String code, String password, boolean active, String name) {
//
//    }

    public User(String firstName, String lastName, String phoneNumber, String email, String encode, Role roleEnum, String generateVerificationCode) {
    }

    public User(Integer id, String firstName, String lastName, String phoneNumber, String position, String generateCode, boolean active, String name) {

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = role.getPermissions()
                .stream()
                .map(permissionEnum -> new SimpleGrantedAuthority(permissionEnum.name()))
                .collect(Collectors.toList());

//        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//        for (PermissionEnum permission : roleEnum.getPermissions()) {
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission.name());
//            simpleGrantedAuthorities.add(simpleGrantedAuthority);
//        }

        return grantedAuthorities;
    }




    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    private boolean enabled;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
}
