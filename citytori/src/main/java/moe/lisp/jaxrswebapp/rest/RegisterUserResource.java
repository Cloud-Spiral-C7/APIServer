package moe.lisp.jaxrswebapp.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import moe.lisp.jaxrswebapp.controller.DBController;
import moe.lisp.jaxrswebapp.entity.QueryUserInfoBase;
import moe.lisp.jaxrswebapp.entity.QueryUserName;

public class RegisterUserResource {

	DBController controller = new DBController();

	@POST
	@Consumes({MediaType.APPLICATION_ATOM_XML,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public QueryUserInfoBase getUserId(QueryUserName userName){

		QueryUserInfoBase userId = new QueryUserInfoBase();

		controller.setUserName(userName.getUserName());
		userId.setUserId(controller.getUserId(userName.getUserName()));
		return userId;
	}

}
