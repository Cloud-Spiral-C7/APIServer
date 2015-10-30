package moe.lisp.jaxrswebapp.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import moe.lisp.jaxrswebapp.controller.DBController;

public class UpdateTimeAttackRanking {

	DBController controller = new DBController();

//	/**
//	 * ランキングとユーザ名を取得
//	 * @param score スコア(Integer型)
//	 * @param userId ユーザID(Cookieと関連付けられている)
//	 * @return ranking ランキング(名前、スコア)
//	 */
//	@GET
//	@Produces({MediaType.APPLICATION_JSON})
//	@Path("/UpdateTimeAttackRanking")
//	public Response UpdateTimeAttackRanking(
//			@QueryParam("userId") final String userId,
//			@QueryParam("resultTime") final int resultTime){
//
//		controller.setRanking(controller.getGameMode(userId) ,  controller.getUserName(userId), resultTime);
//
//
//		return Response.status(200).entity(controller.UpdateTimeAttackRanking(userId,resultTime).toString()).build();
//	}

}
