package ru.kors.repository.impl;

import ru.kors.model.Account;
import ru.kors.repository.AccountRepository;
import ru.kors.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBAccountRepositoryImpl implements AccountRepository {
    private final String SELECT_ALL = "SELECT * FROM test_table";
    private final String INSERT_ACC = "INSERT INTO test_table(email, name, password) VALUES (?, ?, ?);";

    @Override
    public Map<String, Account> allAccounts() {
        Map<String, Account> accounts = new HashMap<>();

        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                double balance = resultSet.getDouble("balance");

                Account account = new Account(id, email, name, password, balance);
                accounts.put(email, account);
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    @Override
    public void saveAccount(Account account) {
        try (Connection connection = ConnectionManager.get();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACC)) {
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountByName(String name) {
        return null;
    }

    @Override
    public void deleteAccount(Account account) {

    }
}
