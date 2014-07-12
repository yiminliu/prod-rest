package com.bedrosians.bedlogic.resources.v2;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bedrosians.bedlogic.service.account.AccountService;
import com.bedrosians.bedlogic.service.security.KeymarkUcUserSecurityService;
import com.bedrosians.bedlogic.util.JsonWrapper.AccountWrapper;
import com.bedrosians.bedlogic.domain.account.Branch;
import com.bedrosians.bedlogic.domain.product.enums.DBOperation;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedDAOExceptionMapper;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.exception.BedResExceptionMapper;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.models.Accounts;

/**
 * Accounts resource
 */
@Controller
@Path("/account")
public class AccountsResource
{
	private static final String domain = "ACCOUNT";
	@Autowired
	private AccountService accountService;
	@Autowired
	KeymarkUcUserSecurityService keymarkUcUserSecurityService;
	
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAccounts(@Context HttpHeaders requestHeaders, @Context UriInfo uriInfo)
    
    {   
        Response response = null;

        try
        {
        	//Check user security
        	keymarkUcUserSecurityService.doUserSecurityCheck(requestHeaders, domain, DBOperation.SEARCH);
            
        	//Retrieve data from database based on the given query parameters
            List<AccountWrapper> accounts = accountService.getAccounts(uriInfo.getQueryParameters());  
            
            //Convert the data to Json string
            Accounts result = new Accounts(accounts);
            
            String jsonStr = result.toJSONStringWithJackson();
       
            //Return json reponse
            response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        }
        catch (BedResUnAuthorizedException e)
        {
        	response = BedResExceptionMapper.MapToResponse(e);
        }
        catch (BedDAOBadParamException e)
        {
        	response = BedDAOExceptionMapper.MapToResponse(e);
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
	

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{customerCode}")
    public Response getAccounts(@Context HttpHeaders requestHeaders, @PathParam("customerCode") String customerCode)
    {     
        Response response = null;

        try
        {
        	//Check user security
        	keymarkUcUserSecurityService.doUserSecurityCheck(requestHeaders, domain, DBOperation.SEARCH);
            
            //Retrieve data from database based on the given query parameters
            List<AccountWrapper> accounts = accountService.getAccountsByCustomerCode(customerCode);  
            
            //Convert the data to Json string
            Accounts result = new Accounts(accounts);
        
            String jsonStr = result.toJSONStringWithJackson();
       
            //Return json reponse
            response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        }
        catch (BedResUnAuthorizedException e)
        {
        	response = BedResExceptionMapper.MapToResponse(e);
        }
        catch (BedDAOBadParamException e)
        {
        	response = BedDAOExceptionMapper.MapToResponse(e);
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
	

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{customercode}/{branchcode}")
    public Response getAccounts(@Context HttpHeaders requestHeaders, @PathParam("customercode") String customerCode, @PathParam("branchcode") String branchCode)
    {
    	Response response = null;

        try
        {
        	//Check user security
        	keymarkUcUserSecurityService.doUserSecurityCheck(requestHeaders, domain, DBOperation.SEARCH);
            
            //Retrieve data from database based on the given query parameters
            Branch branch = accountService.getBranch(customerCode, branchCode);  
            
            //Convert the data to Json string
            Accounts result = new Accounts(branch);
        
            String jsonStr = result.toJSONStringWithJacksonWithRootName();
       
            //Return json reponse
            response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        }
        catch (BedResUnAuthorizedException e)
        {
        	response = BedResExceptionMapper.MapToResponse(e);
        }
        catch (BedDAOBadParamException e)
        {
        	response = BedDAOExceptionMapper.MapToResponse(e);
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
