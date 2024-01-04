package ru.kors.service.impl;

import ru.kors.exception.EmptyFieldException;
import ru.kors.exception.UserIsAlreadyExistsException;
import ru.kors.model.Account;
import ru.kors.repository.AccountRepository;
import ru.kors.service.AccountService;
import ru.kors.util.ObjectFactory;
import ru.kors.util.PasswordHashing;

import java.util.Map;

public class MyAccountServiceImpl implements AccountService {
    private final System.Logger logger = System.getLogger(MyAccountServiceImpl.class.getName());
    private AccountRepository repository;
    private PasswordHashing passwordHashing;

    public MyAccountServiceImpl() {
        try {
            repository = ObjectFactory.getInstance().createObject(AccountRepository.class);
            passwordHashing = ObjectFactory.getInstance().createObject(PasswordHashing.class);
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Cannot find AccountRepository.class impl");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Account> allAccounts() {
        return repository.allAccounts();
    }

    @Override
    public void saveAccount(String name, String password, Double balance) throws UserIsAlreadyExistsException, EmptyFieldException {
        if (repository.allAccounts().containsKey(name)) {
            logger.log(System.Logger.Level.WARNING, name + " is already exists");
            throw new UserIsAlreadyExistsException();
        }
        if (name.equals("") || password.equals("")) {
            logger.log(System.Logger.Level.WARNING, "The field must be filled in");
            throw new EmptyFieldException();
        }

        Account account = new Account(name, password, balance);
        repository.saveAccount(account);
    }

    @Override
    public void saveAccount(String email, String name, String password) throws EmptyFieldException, UserIsAlreadyExistsException {
        if (repository.allAccounts().containsKey(name)) {
            logger.log(System.Logger.Level.WARNING, name + " is already exists");
            throw new UserIsAlreadyExistsException();
        }
        if (email.equals("") || name.equals("") || password.equals("")) {
            logger.log(System.Logger.Level.WARNING, "The field must be filled in");
            throw new EmptyFieldException();
        }

        Long id = Long.valueOf(repository.allAccounts().size() + 1);
        String hashedPwd = passwordHashing.doHashing(email, password);

        Account account = new Account(id, email, name, hashedPwd, 0.00);
        repository.saveAccount(account);
    }
}
