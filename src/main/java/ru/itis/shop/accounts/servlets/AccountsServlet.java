package ru.itis.shop.accounts.servlets;

import ru.itis.shop.accounts.service.AccountsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/accounts/email")
public class AccountsServlet extends HttpServlet {

    private AccountsService accountsService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.accountsService = (AccountsService)
                servletConfig.getServletContext().getAttribute("accountsService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> emails = accountsService.getAllEmails();

        response.setContentType("text/html");

        response.getWriter().println("<ul>");
        for (String email : emails) {
            response.getWriter().println("<li>" + email + "</li>");
        }
        response.getWriter().println("</ul>");
    }
}
