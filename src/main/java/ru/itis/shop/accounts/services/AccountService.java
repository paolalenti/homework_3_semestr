package ru.itis.shop.accounts.services;

import org.springframework.stereotype.Service;
import ru.itis.shop.accounts.dto.AccountDto;
import ru.itis.shop.accounts.repository.AccountRepository;

import java.util.List;

import static ru.itis.shop.accounts.dto.AccountDto.from;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDto> getAll() {
        return from(accountRepository.findAll());
    }

}
