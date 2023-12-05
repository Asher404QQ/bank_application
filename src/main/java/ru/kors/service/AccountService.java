package ru.kors.service;

import ru.kors.exception.EmptyFieldException;
import ru.kors.exception.UserIsAlreadyExistsException;
import ru.kors.model.Account;

import java.util.Map;

public interface AccountService {
    Map<String, Account> allAccounts();
    void saveAccount(String name, String password, Double balance) throws UserIsAlreadyExistsException, EmptyFieldException;
}
