package com.mdk.controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/order/all", "/admin/order/delivering", "/admin/order/details"})
public class OrderController extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURL().toString();

        if (url.contains("admin/order/all")) {
            req.getRequestDispatcher("/views/admin/order/all.jsp").forward(req, resp);
        }
        if (url.contains("admin/order/delivering")) {
            req.getRequestDispatcher("/views/admin/order/delivering.jsp").forward(req, resp);
        }
        if (url.contains("admin/order/details")) {
            req.getRequestDispatcher("/views/admin/order/details.jsp").forward(req, resp);
        }

    }
}
