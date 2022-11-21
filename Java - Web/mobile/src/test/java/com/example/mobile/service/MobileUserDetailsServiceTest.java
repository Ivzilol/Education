package com.example.mobile.service;

import com.example.mobile.model.entity.UserEntity;
import com.example.mobile.model.entity.UserRoleEntity;
import com.example.mobile.model.enums.UserRoleEnum;
import com.example.mobile.model.user.MobileUserDetails;
import com.example.mobile.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MobileUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private MobileUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new MobileUserDetailsService(
                mockUserRepo
        );
    }


    @Test
    void testLoadUserByUsername_UserExists() {
        UserEntity testUserEntity =
                new UserEntity()
                        .setEmail("pesho@example.com")
                        .setPassword("secret")
                        .setFirstName("Peso")
                        .setLastName("Petrov")
                        .setActive(true)
                        .setImageUrl("https://image.com/image")
                        .setUserRoles(
                                List.of(
                                        new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN),
                                        new UserRoleEntity().setUserRole(UserRoleEnum.USER)
                                ));
        when(mockUserRepo.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));
        //act
        MobileUserDetails userDetails = (MobileUserDetails)
        toTest.loadUserByUsername(testUserEntity.getEmail());

        //assert
        Assertions.assertEquals(testUserEntity.getEmail(),
                userDetails.getUsername());
        Assertions.assertEquals(userDetails.getFirstName(), userDetails.getFirstName());
        Assertions.assertEquals(userDetails.getLastName(), userDetails.getLastName());
        Assertions.assertEquals(userDetails.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(userDetails.getFirstName() + " " + testUserEntity.getLastName(),
                userDetails.getFullName());

        var authorities = userDetails.getAuthorities();
        Assertions.assertEquals(2, authorities.size());
        var authoritiesIterator = authorities.iterator();
        Assertions.assertEquals("ROLE_" + UserRoleEnum.ADMIN.name(),
                authoritiesIterator.next().getAuthority());
        Assertions.assertEquals("ROLE_" + UserRoleEnum.USER.name(),
                authoritiesIterator.next().getAuthority());
    }

    @Test
    void testLoadUserByUsername_UserDoseNotExists() {

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("non-existant@example.com")
        );
    }
}
