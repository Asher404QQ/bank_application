package ru.kors.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kors.model.Account;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HelloServlet extends HttpServlet {
    private Map<String, Account> repository;
    @Override
    public void init() throws ServletException {
        repository = new ConcurrentHashMap<>();

        Account user1 = new Account("user1", "password1", 532.31);
        Account user2 = new Account("user2", "password1", 124.01);
        Account user3 = new Account("user3", "password1", 4.00);

        repository.put(user1.getName(), user1);
        repository.put(user2.getName(), user2);
        repository.put(user3.getName(), user3);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("accounts", repository);
        req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (repository.containsKey(name)) doGet(req, resp);
        String password = req.getParameter("password");
        Double balance = Double.parseDouble(req.getParameter("balance"));

        Account account = new Account(name, password, balance);
        repository.put(name, account);
        doGet(req, resp);
    }
}
