package ru.itis.shop.accounts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.shop.accounts.dto.NewAccountDto;
import ru.itis.shop.accounts.services.AccountService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = GET, path = "/{account-id}")
    public ModelAndView getAccount(@PathVariable("account-id") Integer accountId) {
        ModelAndView modelAndView = new ModelAndView("account_page");
        modelAndView.addObject("account", accountService.getById(accountId));
        return modelAndView;
    }

    @RequestMapping(method = POST, path = "/{account-id}/delete")
    public ModelAndView deleteAccount(@PathVariable("account-id") Integer accountId) {
        accountService.delete(accountId);
        return new ModelAndView("redirect:/accounts");
    }

    @RequestMapping(method = GET, path = "/{account-id}/edit")
    public ModelAndView editPage(@PathVariable("account-id") Integer accountId) {
        ModelAndView modelAndView = new ModelAndView("edit_account_page");
        modelAndView.addObject("account", accountService.getById(accountId));
        return modelAndView;
    }

    @RequestMapping(method = POST, path = "/{account-id}/edit")
    public ModelAndView update(
            @PathVariable("account-id") Integer accountId,
            NewAccountDto dto
    ) {
        accountService.update(accountId, dto);
        return new ModelAndView("redirect:/accounts/" + accountId);
    }

    @RequestMapping(method = GET)
    public ModelAndView getAccounts(@RequestParam(value = "q", required = false) String query) {
        ModelAndView modelAndView = new ModelAndView("accounts_page");

        if (query == null || query.isEmpty()) {
            modelAndView.addObject("accounts", accountService.getAll());
        } else {
            modelAndView.addObject("accounts", accountService.search(query));
            modelAndView.addObject("q", query);
        }

        return modelAndView;
    }

    @RequestMapping(method = POST)
    public ModelAndView addNewAccount(NewAccountDto newAccount) {
        accountService.create(newAccount);
        return new ModelAndView("redirect:/accounts");
    }
}
