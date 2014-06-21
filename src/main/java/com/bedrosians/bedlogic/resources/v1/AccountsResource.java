package com.bedrosians.bedlogic.resources.v1;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bedrosians.bedlogic.usercode.UserCodeParser;

import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedDAOExceptionMapper;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.exception.BedResExceptionMapper;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.bedDataAccessDAO.AccountsDAO;
import com.bedrosians.bedlogic.models.Accounts;

@Path("/accounts")
public class AccountsResource
{
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAccounts(@Context HttpHeaders requestHeaders
                                    , @QueryParam("customercode") String customerCode
                                    , @QueryParam("branchcode") String branchCode
                                    , @QueryParam("customername") String customerName
                                    , @QueryParam("creditstatus") String creditStatus)
    {
        return this.getAccountsInternal(requestHeaders, customerCode, branchCode, customerName, creditStatus);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{customercode}")
    public Response getAccounts(@Context HttpHeaders requestHeaders
                                    , @PathParam("customercode") String customerCode)
    {
        return this.getAccountsInternal(requestHeaders, customerCode, "", "", "");
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{customercode}/{branchcode}")
    public Response getAccounts(@Context HttpHeaders requestHeaders
                                    , @PathParam("customercode") String customerCode
                                    , @PathParam("branchcode") String branchCode)
    {
        return this.getAccountsInternal(requestHeaders, customerCode, branchCode, "", "");
    }

    /**
     * Accounts resource
     */
    private Response getAccountsInternal(HttpHeaders requestHeaders
                                    , String customerCode
                                    , String branchCode
                                    , String customerName
                                    , String creditStatus)
    {
        Response    response;

        try
        {
            // Check usercode
            UserCodeParser  userCodeParser = new UserCodeParser(requestHeaders);
            if (!userCodeParser.isValidFormat())
            {
                throw new BedResUnAuthorizedException();
            }
            String userType = userCodeParser.getUserType();
            String userCode = userCodeParser.getUserCode();
            
            // Get query params
            customerCode = (customerCode == null) ? "" : customerCode;
            branchCode = (branchCode == null) ? "" : branchCode;
            customerName = (customerName == null) ? "" : customerName;
            creditStatus = (creditStatus == null) ? "" : creditStatus;
            
            // Retrieve DAO object
            AccountsDAO    accountsDAO = new AccountsDAO();
            Accounts       result = accountsDAO.readAccounts(userType, userCode, customerCode, branchCode, customerName, creditStatus);
            
            // Return json reponse
            String  jsonStr = result.toJSONString();
            response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        }
        catch (BedDAOException e)
        {
            response = BedDAOExceptionMapper.MapToResponse(e);
        }
        catch (BedResException e)
        {
            response = BedResExceptionMapper.MapToResponse(e);
        }
        
        return response;
    }
}
