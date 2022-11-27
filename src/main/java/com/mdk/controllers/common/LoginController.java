package com.mdk.controllers.common;

import com.mdk.models.User;
import com.mdk.services.IUserService;
import com.mdk.services.impl.UserService;
import com.mdk.utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout", "/home"})
public class LoginController extends HttpServlet {
    IUserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURL().toString();
        if (url.contains("login")) {
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else if (url.contains("logout")) {
            SessionUtil.getInstance().removeValue(req, "USERMODEL");
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.getRequestDispatcher("/views/home.jsp").forward(req, resp);;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURL().toString();
        if (url.contains("login")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            User user = userService.findOneByUsernameAndPassword(username, password);
            if (user != null) {
                SessionUtil.getInstance().putValue(req, "USERMODEL", user);
                if (user.getRole().equals("ADMIN")) {
                    resp.sendRedirect(req.getContextPath() + "/admin/user/all");
                } else if (user.getRole().equals("USER")) {
                    resp.sendRedirect(req.getContextPath() + "/vendor/home");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/login");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        }
    }
}
