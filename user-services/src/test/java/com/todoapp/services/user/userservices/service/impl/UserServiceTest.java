package com.todoapp.services.user.userservices.service.impl;

import com.todoapp.services.user.userservices.client.util.UtilService;
import com.todoapp.services.user.userservices.model.User;
import com.todoapp.services.user.userservices.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;

import static com.todoapp.services.user.userservices.util.Constants.DP_GET_ALL_USERS_DATA_PROVIDER;
import static com.todoapp.services.user.userservices.util.Constants.EMAIL;
import static com.todoapp.services.user.userservices.util.Constants.GITHUB_ID;
import static com.todoapp.services.user.userservices.util.Constants.GOOGLE_ID;
import static com.todoapp.services.user.userservices.util.Constants.ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserRepository userRepository;
    private UtilService utilService;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        utilService = mock(UtilService.class);
    }

    private static Object[][] getAllUsersDataProvider() {
        return new Object[][] {
                {
                        Collections.emptyList()
                },
                {
                        List.of(
                                new User.Builder()
                                        .withId(ID)
                                        .withEmail(EMAIL)
                                        .withGithubId(GITHUB_ID)
                                        .withGoogleId(GOOGLE_ID)
                                        .build()
                        )
                }
        };
    }

    @ParameterizedTest
    @MethodSource(DP_GET_ALL_USERS_DATA_PROVIDER)
    void test_getAllUsers(List<User> users) {
        // GIVEN

        // WHEN
        when(userRepository.findAll()).thenReturn(users);

        userService = createUserService();

        // THEN
        assertEquals(users, userService.getAllUsers());

        // VERIFY
        verifyNoInteractions(utilService);
        verify(userRepository, times(1)).findAll();
    }

    private UserService createUserService() {
        return new UserService(userRepository, utilService);
    }
}
