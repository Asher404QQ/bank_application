package ru.kors.repository;

import ru.kors.model.Account;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class FakeStorage {
    private static final FakeStorage storage;
    private Map<String, Account> repository;

    static {
        storage = new FakeStorage();
    }

    private FakeStorage() {
        this.repository = new ConcurrentHashMap<>();
    }

    public static FakeStorage storage(){
        return storage;
    }

    public Map<String, Account> allAccounts() {
        return repository;
    }
}
