package ru.itis.shop.accounts.servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.itis.shop.accounts.dto.AccountDto;
import ru.itis.shop.accounts.service.AccountsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/accounts/search")
public class SearchServlet extends HttpServlet {

    private AccountsService accountsService;
    private Template accountsTemplate;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.accountsService = (AccountsService)
                servletConfig.getServletContext().getAttribute("accountsService");
        try {
            accountsTemplate = ((Configuration)servletConfig.getServletContext().getAttribute("freemarkerConfiguration"))
                    .getTemplate("accounts_page.ftlh");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("q");
        List<AccountDto> accounts = accountsService.search(query);

        response.setContentType("text/html");

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("accounts", accounts);

        try {
            accountsTemplate.process(attributes, response.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

//        response.getWriter().println("<table>");
//        response.getWriter().println("  <tr>");
//        response.getWriter().println("      <th>First Name</th>");
//        response.getWriter().println("      <th>Last Name</th>");
//        response.getWriter().println("      <th>Email</th>");
//        response.getWriter().println("  </tr>");
//
//        for (AccountDto account : accounts) {
//            response.getWriter().println("<tr>");
//            response.getWriter().println("      <td>" + account.getFirstName() + "</td>");
//            response.getWriter().println("      <td>" + account.getLastName() + "</td>");
//            response.getWriter().println("      <td>" + account.getEmail() + "</td>");
//            response.getWriter().println("</tr>");
//        }
//        response.getWriter().println("</table>");


    }
}
