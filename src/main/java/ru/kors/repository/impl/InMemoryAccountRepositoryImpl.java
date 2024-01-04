package ru.kors.repository.impl;

import ru.kors.model.Account;
import ru.kors.repository.AccountRepository;
import ru.kors.repository.FakeStorage;

import java.util.Map;

public class InMemoryAccountRepositoryImpl implements AccountRepository {
    private final System.Logger logger = System.getLogger(InMemoryAccountRepositoryImpl.class.getName());

    @Override
    public Map<String, Account> allAccounts() {
        return FakeStorage.storage().allAccounts();
    }

    @Override
    public void saveAccount(Account account) {
        FakeStorage.storage().allAccounts().put(account.getEmail(), account);
        logger.log(System.Logger.Level.INFO, "Save account: {0}", account.toString());
    }

    @Override
    public Account findAccountByName(String name) {
        return FakeStorage.storage().allAccounts().get(name);
    }

    @Override
    public void deleteAccount(Account account) {
        FakeStorage.storage().allAccounts().remove(account.getName(), account);
        logger.log(System.Logger.Level.INFO, "Delete account: {0}", account.toString());
    }
}
