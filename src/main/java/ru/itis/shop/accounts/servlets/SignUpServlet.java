package ru.itis.shop.accounts.servlets;

import ru.itis.shop.accounts.service.AccountsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/accounts/signUp")
public class SignUpServlet extends HttpServlet {

    private AccountsService accountsService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.accountsService = (AccountsService)
                servletConfig.getServletContext().getAttribute("accountsService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<form action=\"#\" method=\"post\" >\n" +
                "    <label for=\"firstName\">First Name</label>\n" +
                "    <input type=\"text\" id=\"firstName\" name=\"firstName\" required>\n" +
                "\n" +
                "    <label for=\"lastName\">Last Name</label>\n" +
                "    <input type=\"text\" id=\"lastName\" name=\"lastName\" required>\n" +
                "\n" +
                "    <label for=\"age\">Age</label>\n" +
                "    <input type=\"number\" id=\"age\" name=\"age\" required>\n" +
                "\n" +
                "    <label for=\"email\">Email</label>\n" +
                "    <input type=\"email\" id=\"email\" name=\"email\" required>\n" +
                "\n" +
                "    <label for=\"password\">Password</label>\n" +
                "    <input type=\"password\" id=\"password\" name=\"password\" required>\n" +
                "\n" +
                "    <input type=\"submit\" value=\"Submit\">\n" +
                "</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String age = request.getParameter("age");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(firstName + " " + lastName + " " + age +" " + email + " " + password);

        this.accountsService.signUp(firstName, lastName, email, password, Integer.parseInt(age));

        response.sendRedirect("/simple-app/accounts/email");
    }
}
