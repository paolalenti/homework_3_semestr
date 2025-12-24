package ru.itis.shop.accounts.dto;

import ru.itis.shop.accounts.entity.Account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountDto {

    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String email;

    private AccountDto(Integer id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static AccountDto from(Account account) {
        return new AccountDto(account.getId(),
                account.getFirstName(),
                account.getLastName(),
                account.getEmail());
    }

    public static List<AccountDto> from(List<Account> accounts) {
        return accounts.stream().map(AccountDto::from).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
