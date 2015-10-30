package moe.lisp.jaxrswebapp.rest;

import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import moe.lisp.jaxrswebapp.controller.DBController;
import moe.lisp.jaxrswebapp.entity.ResponseInitGameInfo;
import moe.lisp.jaxrswebapp.entity.QueryUserInfoBase;



@Path("InitTimeAtk")
public class InitTimeAtkResource {

	DBController controller = new DBController();
	
	@POST
	@Consumes({MediaType.APPLICATION_ATOM_XML,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseInitGameInfo getGameInfo(QueryUserInfoBase userInfo){
		
		String currentWord = generateTheme();

		ResponseInitGameInfo info = new ResponseInitGameInfo();
		info.setLatLon(controller.getLatLon(userInfo.getUserId()));
		info.setTheme(currentWord);
		info.setWordNum(controller.getWordNum(userInfo.getUserId()));

		controller.setStatus(userInfo.getUserId() , DBController.ROOM_STATUS_PLAYING);
		controller.setCurrentWord(userInfo.getUserId() , currentWord);

		return info;
	}


	//This method is generate a theme.
	private String generateTheme(){

		String[] dictionary = {"あ","い","う","え","お","か","き","く","け","こ","さ","し","す","せ","そ","た","ち","つ","て","と","な","に","ぬ","ね","の","は","ひ","ふ","へ","ほ","ま","み","む","め","も","や","ゆ","よ","わ"};
		Random rnd = new Random();

		return dictionary[rnd.nextInt(dictionary.length)];
	}

}
