package ru.itis.shop.accounts.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.itis.shop.accounts.services.AccountService;

public class AccountController implements Controller {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals(HttpMethod.GET.name())) {
            ModelAndView modelAndView = new ModelAndView("accounts_page");
            modelAndView.addObject("accounts", accountService.getAll());
            return modelAndView;
        } else {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }
}
