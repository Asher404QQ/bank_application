package ru.kors.service.impl;

import ru.kors.exception.EmptyFieldException;
import ru.kors.exception.UserIsAlreadyExistsException;
import ru.kors.model.Account;
import ru.kors.repository.AccountRepository;
import ru.kors.repository.impl.InMemoryAccountRepositoryImpl;
import ru.kors.service.AccountService;
import ru.kors.util.ObjectFactory;

import java.util.Map;

public class MyAccountServiceImpl implements AccountService {
    private AccountRepository repository;

    public MyAccountServiceImpl() {
        try {
            repository = ObjectFactory.getInstance().createObject(AccountRepository.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Account> allAccounts() {
        return repository.allAccounts();
    }

    @Override
    public void saveAccount(String name, String password, Double balance) throws UserIsAlreadyExistsException, EmptyFieldException {
        if (repository.allAccounts().containsKey(name)) throw new UserIsAlreadyExistsException(name + " is already exists");
        if (name.equals("") || password.equals("")) throw new EmptyFieldException("The field must be filled in");

        Account account = new Account(name, password, balance);
        repository.saveAccount(account);
    }
}
