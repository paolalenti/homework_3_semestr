package ru.itis.shop.accounts.services;

import org.springframework.stereotype.Service;
import ru.itis.shop.accounts.dto.AccountDto;
import ru.itis.shop.accounts.dto.NewAccountDto;
import ru.itis.shop.accounts.entity.Account;
import ru.itis.shop.accounts.repository.AccountRepository;
import ru.itis.shop.accounts.repository.AccountRepositoryJdbcTemplateImpl;

import java.util.List;

import static ru.itis.shop.accounts.dto.AccountDto.from;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountRepositoryJdbcTemplateImpl accountRepositoryJdbcTemplateImpl;

    public AccountService(AccountRepository accountRepository, AccountRepositoryJdbcTemplateImpl accountRepositoryJdbcTemplateImpl) {
        this.accountRepository = accountRepository;
        this.accountRepositoryJdbcTemplateImpl = accountRepositoryJdbcTemplateImpl;
    }

    public List<AccountDto> getAll() {
        return from(accountRepository.findAll());
    }

    public AccountDto getById(int id) {
        return from(accountRepository.findById(id));
    }

    public void create(NewAccountDto newAccount) {

        Account account = new Account(
                newAccount.getFirstName(),
                newAccount.getLastName(),
                newAccount.getEmail(),
                newAccount.getPassword(),
                newAccount.getAge()
        );

        accountRepository.save(account);
    };

    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    public Account update(Integer id, NewAccountDto dto) {

        Account old = accountRepository.findById(id);
        if (old == null) return null;

        Account updated = new Account(
                id,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                old.getPassword(),
                old.getAge()
        ); 

        accountRepository.update(updated);

        return updated;
    }

    public List<AccountDto> search(String query) {
        return from(accountRepository.search(query));
    }
}
