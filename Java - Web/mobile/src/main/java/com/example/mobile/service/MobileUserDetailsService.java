package com.example.mobile.service;

import com.example.mobile.model.entity.UserEntity;
import com.example.mobile.model.entity.UserRoleEntity;
import com.example.mobile.model.user.MobileUserDetails;
import com.example.mobile.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class MobileUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MobileUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with email" + username +
                        "not found!"));
    }

    private UserDetails map(UserEntity userEntity) {

        return new MobileUserDetails(
                userEntity.getPassword(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity
                        .getUserRoles()
                        .stream()
                        .map(this::map)
                        .toList()
        );
    }

    private GrantedAuthority map(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.getUserRole()
                        .name());
    }
}
