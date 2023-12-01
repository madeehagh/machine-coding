package com.splitwise.service;

import com.splitwise.entity.User;
import com.splitwise.entity.UserBalance;
import com.splitwise.enums.UserStatus;
import com.splitwise.respository.UserBalanceRepository;
import com.splitwise.respository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SplitServiceImplTest {

    @InjectMocks
    private SplitServiceImpl splitService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserBalanceRepository userBalanceRepository;

    private User user;
    private UserBalance userBalance;

    @BeforeEach
    public void setUp() {
        user = new User(

                "Test User",
                "testuser@gmail.com",
                "1234567890");
    }

    @Test
    public void testRegisterUserWhenCalledThenUserSaved() {
        when(userRepository.save(user)).thenReturn(user);
        ArgumentCaptor<UserBalance> userBalanceCaptor = ArgumentCaptor.forClass(UserBalance.class);
        when(userBalanceRepository.save(userBalanceCaptor.capture())).thenReturn(null);

        splitService.registerUser(user);

        verify(userRepository, times(1)).save(user);
        verify(userBalanceRepository, times(1)).save(any(UserBalance.class));

        UserBalance savedUserBalance = userBalanceCaptor.getValue();
        assertEquals(user.getId(), savedUserBalance.getUserId());
        assertEquals(0.0, savedUserBalance.getBalance());
    }

    @Test
    public void testRegisterUserWhenNullUserThenUserNotSaved() {
        splitService.registerUser(null);

        verify(userRepository, times(0)).save(any(User.class));
        verify(userBalanceRepository, times(0)).save(any(UserBalance.class));
    }

    @Test
    public void testRegisterUserWhenExceptionThrownThenPropagateException() {
        when(userRepository.save(user)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> splitService.registerUser(user));

        verify(userRepository, times(1)).save(user);
        verify(userBalanceRepository, times(0)).save(any(UserBalance.class));
    }

    @Test
    public void testSendInviteWhenRegisteredUserFoundThenReturnRegistered() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        UserStatus status = splitService.sendInvite(String.valueOf(user.getId()));

        assertEquals(UserStatus.REGISTERED, status);
    }

    @Test
    public void testSendInviteWhenUserNotFoundThenReturnNotFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        UserStatus status = splitService.sendInvite(String.valueOf(user.getId()));

        assertEquals(UserStatus.NOT_FOUND, status);
    }
}