package ru.kors.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.kors.service.AccountService;
import ru.kors.util.ObjectFactory;
import ru.kors.util.PasswordHashing;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private AccountService service;
    private PasswordHashing passwordHashing;
    private final System.Logger logger = System.getLogger(LoginServlet.class.getName());

    @Override
    public void init() throws ServletException {
        try {
            service = ObjectFactory.getInstance().createObject(AccountService.class);
            passwordHashing = ObjectFactory.getInstance().createObject(PasswordHashing.class);
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, e);
            throw new RuntimeException();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String hashedPwd = passwordHashing.doHashing(email, password);
        if (service.allAccounts().containsKey(email) &&
        service.allAccounts().get(email).getPassword().equals(hashedPwd)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", email);
            resp.sendRedirect("/hello");
        } else {
            doGet(req, resp);
        }
    }
}
