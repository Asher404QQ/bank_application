package ru.kors.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kors.exception.EmptyFieldException;
import ru.kors.exception.UserIsAlreadyExistsException;
import ru.kors.service.AccountService;
import ru.kors.util.ObjectFactory;

import java.io.IOException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private AccountService service;
    private final System.Logger logger = System.getLogger(SignUpServlet.class.getName());

    @Override
    public void init() throws ServletException {
        try {
            service = ObjectFactory.getInstance().createObject(AccountService.class);
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            service.saveAccount(req.getParameter("email"),
                    req.getParameter("name"),
                    req.getParameter("password"));
            resp.sendRedirect("/login");
        } catch (EmptyFieldException | UserIsAlreadyExistsException e) {
            doGet(req, resp);
        }
    }
}
