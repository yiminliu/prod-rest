package com.bedrosians.bedlogic.keymarkDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bedrosians.bedlogic.models.Account;

@Repository
public class AccountDAO
{
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Account getAccount(String custcd)
    {
        String SQL = "SELECT custcd, coname, coaddr1, coaddr2, cocity, costatecd, cozip, cocountrycd FROM arm WHERE custcd = ?";
        Account account = this.jdbcTemplate.queryForObject(SQL, new Object[]{custcd}, new RowMapper<Account>() {
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                return new Account(rs.getString("custcd"), rs.getString("coname"), rs.getString("coaddr1"), rs.getString("coaddr2"), rs.getString("cocity"), rs.getString("costatecd"), rs.getString("cozip"), rs.getString("cocountrycd"));
            }
        });
        
        return account;
    }
}