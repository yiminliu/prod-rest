package com.bedrosians.bedlogic.resources;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.bedrosians.bedlogic.models.Account;
import com.bedrosians.bedlogic.keymarkDAO.AccountDAO;

@Repository
@Path("/accounts")
public class AccountsResource {
    @Autowired
    private AccountDAO accountDAO;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Account getAccounts() {
        Account     account = this.accountDAO.getAccount("24276");
        
        return account;
    }    
}
