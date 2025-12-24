package ru.itis.shop.accounts.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.itis.shop.accounts.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRepositoryJdbcTemplateImpl implements AccountRepository {

    private static final String SQL_UPDATE_ACCOUNT =
            "update account set first_name = ?, last_name = ?, email = ?, password = ?, age = ? where id = ?";

    private static final String SQL_DELETE_ACCOUNT_BY_ID = "DELETE FROM account WHERE id = ?";

    private static final String SQL_SELECT_ALL_FROM_ACCOUNT = "select * from account";

    private static final String SQL_SELECT_FROM_ACCOUNT = "select * from account where id = ?";

    //language=sql
    private static final String SQL_SEARCH_SELECT = "select * from account where " +
            "first_name ilike ? or " +
            "last_name ilike ? or " +
            "email ilike ?";


    private final JdbcTemplate jdbcTemplate;

    public AccountRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Account> accountRowMapper = new RowMapper<Account>() {
        @Override
        public Account mapRow(ResultSet row, int rowNum) throws SQLException {
            return new Account(row.getInt("id"),
                    row.getString("first_name"),
                    row.getString("last_name"),
                    row.getString("email"),
                    row.getString("password"),
                    row.getInt("age"));
        }
    };

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_FROM_ACCOUNT, accountRowMapper);
    }

    @Override
    public void save(Account account) {
        Map<String, Object> params = new HashMap<>();
        params.put("first_name", account.getFirstName());
        params.put("last_name", account.getLastName());
        params.put("email", account.getEmail());
        params.put("password", account.getPassword());
        params.put("age", account.getAge());

        Integer id =  new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("account").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(params).intValue();

        account.setId(id);
    }

    @Override
    public Account findById(Integer id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_FROM_ACCOUNT, accountRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Account> search(String query) {
        String preparedQuery = "%" + query + "%";
        return jdbcTemplate.query(SQL_SEARCH_SELECT, accountRowMapper,
                preparedQuery,
                preparedQuery,
                preparedQuery);
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update(SQL_DELETE_ACCOUNT_BY_ID, id);
    }

    @Override
    public void update(Account account) {
        jdbcTemplate.update(SQL_UPDATE_ACCOUNT,
                account.getFirstName(),
                account.getLastName(),
                account.getEmail(),
                account.getPassword(),
                account.getAge(),
                account.getId()
        );
    }
}
