package ru.kors.service.impl;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kors.exception.EmptyFieldException;
import ru.kors.exception.UserIsAlreadyExistsException;
import ru.kors.model.Account;
import ru.kors.repository.AccountRepository;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MyAccountServiceImplTest {
    @Mock
    private AccountRepository repository;
    @InjectMocks
    private MyAccountServiceImpl service;

    @Test
    void shouldReturnAllAccounts() {
        Account account1 = new Account("account1", "password1", 21.1);
        Account account2 = new Account("account2", "password1", 21.1);
        Account account3 = new Account("account3", "password1", 21.1);

        Mockito.when(repository.allAccounts()).thenReturn(Map.of(account1.getName(), account1,
                account2.getName(), account2,
                account3.getName(), account3));

        Map<String, Account> expected = repository.allAccounts();
        Map<String, Account> actual = service.allAccounts();

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowUserIsAlreadyExistsException() {
        Account account1 = new Account("account1", "password1", 21.1);

        Mockito.when(repository.allAccounts()).thenReturn(Map.of(account1.getName(), account1));

        assertThrows(UserIsAlreadyExistsException.class, () -> service.saveAccount(account1.getName(),
                account1.getPassword(), account1.getBalance()));
    }

    @Test
    void EmptyFieldExceptionByEmptyName() {
        assertThrows(EmptyFieldException.class, () -> service.saveAccount("", "ldskfajlsdf", 32.3));
    }
    @Test
    void EmptyFieldExceptionByEmptyPassword() {
        assertThrows(EmptyFieldException.class, () -> service.saveAccount("dsafdsa", "", 32.3));
    }

    @Test
    void shouldSaveAccountWithoutException() {
        String name = "account";
        String password = "password";
        Double balance = 32.1;

        assertDoesNotThrow(() -> service.saveAccount(name, password, balance));
    }

}