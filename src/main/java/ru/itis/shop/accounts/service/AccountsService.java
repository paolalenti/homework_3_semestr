package ru.itis.shop.accounts.service;

import org.springframework.stereotype.Service;
import ru.itis.shop.accounts.dto.AccountDto;
import ru.itis.shop.accounts.entity.Account;
import ru.itis.shop.accounts.repository.AccountsRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.itis.shop.accounts.dto.AccountDto.from;

@Service
public class AccountsService {

    private final AccountsRepository accountsRepository;

    public AccountsService(AccountsRepository accountsRepository) {
        System.out.println("NEW OBJECT ACCOUNTS SERVICE");
        this.accountsRepository = accountsRepository;
    }

    public List<String> getAllEmails() {
        return accountsRepository.findAll()
                .stream()
                .map(Account::getEmail)
                .collect(Collectors.toList());
    }

    public List<AccountDto> search(String query) {
        return from(accountsRepository.search(query));
    }

    public void signUp(String firstName, String lastName, String email, String password, int age) {
        Account account = new Account(firstName, lastName, email, password, age);

        accountsRepository.save(account);
    }

}
