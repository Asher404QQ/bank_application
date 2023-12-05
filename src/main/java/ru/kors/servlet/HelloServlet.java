package ru.kors.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kors.exception.EmptyFieldException;
import ru.kors.exception.UserIsAlreadyExistsException;
import ru.kors.service.AccountService;
import ru.kors.service.impl.MyAccountServiceImpl;
import ru.kors.util.ObjectFactory;

import java.io.IOException;

public class HelloServlet extends HttpServlet {
    private AccountService service;
    @Override
    public void init() throws ServletException {
        try {
            service = ObjectFactory.getInstance().createObject(AccountService.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("accounts", service.allAccounts().values());
        req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            service.saveAccount(req.getParameter("name"),
                    req.getParameter("password"),
                    Double.parseDouble(req.getParameter("balance")));
        } catch (UserIsAlreadyExistsException e) {
            req.setAttribute("accountExistsError", "true");
        } catch (EmptyFieldException e) {
            req.setAttribute("nullError", "true");
        } finally {
            doGet(req, resp);
        }
    }
}
