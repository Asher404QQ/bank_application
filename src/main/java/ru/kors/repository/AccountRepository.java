package ru.kors.repository;

import ru.kors.model.Account;

import java.util.Map;

public interface AccountRepository {
    Map<String, Account> allAccounts();
    void saveAccount(Account account);
    Account findAccountByName(String name);
    void deleteAccount(Account account);
}
