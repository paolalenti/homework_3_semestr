package ru.itis.shop.accounts.servlets;

import ru.itis.shop.accounts.dto.AccountDto;
import ru.itis.shop.accounts.service.AccountsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/accounts/search")
public class SearchServlet extends HttpServlet {

    private AccountsService accountsService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.accountsService = (AccountsService)
                servletConfig.getServletContext().getAttribute("accountsService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("q");
        List<AccountDto> accounts = accountsService.search(query);

        response.setContentType("text/html");

        response.getWriter().println("<table>");
        response.getWriter().println("  <tr>");
        response.getWriter().println("      <th>First Name</th>");
        response.getWriter().println("      <th>Last Name</th>");
        response.getWriter().println("      <th>Email</th>");
        response.getWriter().println("  </tr>");

        for (AccountDto account : accounts) {
            response.getWriter().println("<tr>");
            response.getWriter().println("      <td>" + account.getFirstName() + "</td>");
            response.getWriter().println("      <td>" + account.getLastName() + "</td>");
            response.getWriter().println("      <td>" + account.getEmail() + "</td>");
            response.getWriter().println("</tr>");
        }
        response.getWriter().println("</table>");


    }
}
