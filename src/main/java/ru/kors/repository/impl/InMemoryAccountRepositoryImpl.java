package ru.kors.repository.impl;

import ru.kors.model.Account;
import ru.kors.repository.AccountRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryAccountRepositoryImpl implements AccountRepository {
    private Map<String, Account> repository;

    public InMemoryAccountRepositoryImpl() {
        repository = new ConcurrentHashMap<>();
        loadAccInDB();
    }

    private void loadAccInDB() {
        Account user1 = new Account("user1", "password1", 532.31);
        Account user2 = new Account("user2", "password1", 124.01);
        Account user3 = new Account("user3", "password1", 4.00);

        repository.put(user1.getName(), user1);
        repository.put(user2.getName(), user2);
        repository.put(user3.getName(), user3);
    }

    @Override
    public Map<String, Account> allAccounts() {
        return repository;
    }

    @Override
    public void saveAccount(Account account) {
        repository.put(account.getName(), account);
    }
}
