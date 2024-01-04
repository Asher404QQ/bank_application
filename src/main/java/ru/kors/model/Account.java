package ru.kors.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(columnDefinition = "NUMERIC(15, 2)", nullable = false)
    private Double balance = 0.00;

    public Account(){}
    @Deprecated(forRemoval = true, since = "Use the constructor with email")
    public Account(String name, String password, Double balance) {
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public Account(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Account(Long id, String email, String name, String password, Double balance) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(email, account.email) && Objects.equals(name, account.name) && Objects.equals(password, account.password) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, password, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
