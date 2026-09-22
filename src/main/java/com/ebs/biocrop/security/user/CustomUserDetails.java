package com.ebs.biocrop.security.user;

import com.ebs.biocrop.entity.User;
import com.ebs.biocrop.entity.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final String id;
    private final String phoneNumber;
    private final UserRole role;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String id, String phoneNumber, UserRole role, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.role = role != null ? role : UserRole.ROLE_CUSTOMER;
        this.authorities = authorities;
    }

    public static CustomUserDetails build(User user) {
        UserRole userRole = user.getRole() != null ? user.getRole() : UserRole.ROLE_CUSTOMER;
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userRole.name()));
        return new CustomUserDetails(
                user.getId(),
                user.getPhoneNumber(),
                userRole,
                authorities
        );
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return ""; // Passwordless OTP-based authentication
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
