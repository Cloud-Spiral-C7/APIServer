package moe.lisp.jaxrswebapp.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import moe.lisp.jaxrswebapp.controller.DBController;

@Path("CloseSingleGame")
public class CloseSingleGame {


	DBController controller = new DBController();
	/**
	 * 一人用タイムアタックを終了
	 * @param userId ユーザID(Cookieと関連付けられている)
	 *
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response CloseSingleGame(
			@QueryParam("userId") final String userId){
		//一応値は返すが、何にも使われない
		controller.CloseSingleGame(userId,controller.getRoomId(userId));
		return Response.status(200).entity("").build();
	}

}
