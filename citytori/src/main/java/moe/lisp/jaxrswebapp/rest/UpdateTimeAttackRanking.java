package moe.lisp.jaxrswebapp.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import moe.lisp.jaxrswebapp.controller.DBController;
import moe.lisp.jaxrswebapp.entity.ResponseRanking;
import moe.lisp.jaxrswebapp.entity.Rank;


@Path("UpdateTimeAttackRanking")
public class UpdateTimeAttackRanking {

	DBController controller = new DBController();

	/**
	 * ランキングとユーザ名を取得
	 * @param score スコア(Integer型)
	 * @param userId ユーザID(Cookieと関連付けられている)
	 * @return ranking ResponseRanking型
	 * 		例:{
			“userName” : ”hogehoge”,
			“ranking” : [
			{“name” : ”hoge” , ”score” : 198},
			{“name” : ”foo” , ”score” : 199},
			{“name” : ”bar” , ”score” : 200}
						]
				}
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseRanking UpdateTimeAttackRankingSource(
			@QueryParam("userId") String userId
			,@QueryParam("resultTime") String resultTime
			){
		ResponseRanking response = new ResponseRanking();
		ArrayList<Rank> ranking = new ArrayList<Rank>();
		controller.setStatus(userId, "Result");
		controller.setRanking(controller.getGameMode(userId) ,  controller.getUserName(userId), resultTime);
		ranking = controller.getRanking(controller.getGameMode(userId));
		response.setUserName(controller.getUserName(userId));
		response.setRankings(ranking);
		return response;
		}

//	@GET
//	@Path("/t")
//	@Produces({MediaType.APPLICATION_JSON})
//	public SampleResponse t() {
//		return new SampleResponse();
//	}
}
