package com.qa.endpoint;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qa.business.BusinessLogic;
import com.qa.repository.AccountDBRepository;
import com.qa.service.ServiceInterface;
import com.qa.util.JSONUtil;

@Path("/account")
public class AccountEndpoint {
	
	@Inject
	private ServiceInterface service;
			
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllAccounts() {
		return service.getAllAccounts();
	}
	
	@POST
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createAccount(String account) {
		return service.createAccount(account);
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findAccount(@PathParam("id") Long id) {
		return service.findAccount(id);
	}
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateAccount(@PathParam("id") Long id, String account) {
		return service.updateAccount(id, account);
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAccount(@PathParam("id") Long id) {
		return service.deleteAccount(id);
	}

}
