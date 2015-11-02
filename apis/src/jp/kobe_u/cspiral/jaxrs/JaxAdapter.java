package jp.kobe_u.cspiral.jaxrs;

import java.net.URLDecoder;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jp.kobe_u.cspiral.controller.DBController;
import jp.kobe_u.cspiral.entity.QueryAnswer;
import jp.kobe_u.cspiral.entity.QuerySession;
import jp.kobe_u.cspiral.entity.ResponseAnswer;
import jp.kobe_u.cspiral.entity.ResponseInitialValue;
import jp.kobe_u.cspiral.entity.ResponseSession;

@Path("/api")
public class JaxAdapter {

	DBController controller = new DBController();


	/**
	 * ユーザIDを返す
	 * @param 任意のユーザ名
	 * @return ユーザID
	 */
	@POST
	@Consumes({MediaType.APPLICATION_ATOM_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/session")
	public Response getSession(QuerySession query){

		ResponseSession session = new ResponseSession();

		controller.setUserName(query.getUserName());
		session.setUserId(controller.getUserId(query.getUserName()));

		return Response.status(200).entity(session).build();
	}

	/**
	 * 新規ルームを作成する
	 * @param ルーム名、ゲームモード
	 */
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/rooms")
	public void setRooms(){

	}

	/**
	 * ルーム一覧を返す
	 * @return ルーム名、ゲームモードの配列
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/rooms")
	public void getRooms(){

	}

	/**
	 * ゲームの初期値を返す
	 * @param ユーザID
	 * @return お題、経緯度、目標数
	 */
	@GET
	@Consumes({MediaType.APPLICATION_ATOM_XML,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/rooms/initialValue")
	public Response getInitialValue(@QueryParam("userId") String userId){

		ResponseInitialValue value = new ResponseInitialValue();

		String currentWord = generateTheme();

		value.setLatLon(controller.getLatLon(userId));
		value.setTheme(currentWord);
		value.setWordNum(controller.getWordNum(userId));

		controller.setStatus(userId , DBController.ROOM_STATUS_PLAYING);
		controller.setCurrentWord(userId , currentWord);

		return Response.status(200).entity(value).build();

	}

	/**
	 * しりとりが出来ているかを判定する
	 * @param ユーザID、地名、地名の読み
	 * @return 判定値、次のお題
	 */
	@POST
	@Consumes({MediaType.APPLICATION_ATOM_XML,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/rooms/answers")
	public Response judgeAnswer(QueryAnswer query){

		ResponseAnswer answer = new ResponseAnswer();

		String phonetic = "";
		String locationName = "";
		try{
			phonetic = URLDecoder.decode(query.getPhonetic(),"utf-8");
			locationName = URLDecoder.decode(query.getLocationName(),"utf-8");
		}catch (Exception e){

		}

		boolean isCurrentWord = checkCurrentWord(query.getUserId(), phonetic);
		boolean isNewWord = checkNewWord(query.getUserId(), phonetic);

		if(isCurrentWord){
			if(isNewWord){
				String currentWord = convertSonantMark(phonetic.substring(phonetic.length() - 1));
				controller.setCurrentWord(query.getUserId(), currentWord);
				controller.setAnswer(query.getUserId(), locationName , phonetic);
				answer.setNextStringWith(currentWord);
				answer.setLocationName(locationName);
				answer.setPhonetic(phonetic);
				if(checkFinish(query.getUserId())){
					answer.setResult("Finish");
				}else{
					answer.setResult("OK");
				}
			}else{
				answer.setResult("NG:New");
			}
		}else{

			answer.setResult("NG:Current");
		}

		return Response.status(200).entity(answer).build();

	}


	/**
	 * お題をランダムに生成する
	 * @return お題
	 */
	private String generateTheme(){

		String[] dictionary = {"あ","い","う","え","お","か","き","く","け","こ","さ","し","す","せ","そ","た","ち","つ","て","と","な","に","ぬ","ね","の","は","ひ","ふ","へ","ほ","ま","み","む","め","も","や","ゆ","よ","わ"};
		Random rnd = new Random();

		return dictionary[rnd.nextInt(dictionary.length)];
	}


	/**
	 * しりとりできているかを確認する
	 * @param ユーザID、解答
	 * @return boolean
	 */
	private boolean checkCurrentWord(String userId, String phonetic){

		String firstChar = phonetic.substring(0,1);
		firstChar = convertSonantMark(firstChar);
		String currentWord = controller.getCurrentWord(userId);
		if(firstChar.equals(currentWord)){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * 既出単語でないか確認する
	 * @param ユーザID、解答
	 * @return boolean
	 */
	private boolean checkNewWord(String userId, String phonetic){

		long answerNum = controller.getAnswerNum(userId , phonetic);
		if(answerNum == 0){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 目標数に達しているか確認する
	 * @param ユーザID
	 * @return boolean
	 */
	private boolean checkFinish(String userId){
		long goal = (long)controller.getWordNum(userId);
		long current = controller.getAnswerNum(userId);
		if((goal - current) == 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 濁点、半濁点を除去する
	 * @param 1文字
	 * @return 濁点、半濁点を除去した文字
	 */
	private String convertSonantMark(String oneWord){
		String convertedWord = oneWord;
		if(oneWord.length() == 1){
			if(oneWord.equals("が")){
				convertedWord = "か";
			}else if(oneWord.equals("ぎ")){
				convertedWord = "き";
			}else if(oneWord.equals("ぐ")){
				convertedWord = "く";
			}else if(oneWord.equals("げ")){
				convertedWord = "け";
			}else if(oneWord.equals("ご")){
				convertedWord = "こ";
			}else if(oneWord.equals("ざ")){
				convertedWord = "さ";
			}else if(oneWord.equals("じ")){
				convertedWord = "し";
			}else if(oneWord.equals("ず")){
				convertedWord = "す";
			}else if(oneWord.equals("ぜ")){
				convertedWord = "せ";
			}else if(oneWord.equals("ぞ")){
				convertedWord = "そ";
			}else if(oneWord.equals("だ")){
				convertedWord = "た";
			}else if(oneWord.equals("ぢ")){
				convertedWord = "ち";
			}else if(oneWord.equals("づ")){
				convertedWord = "つ";
			}else if(oneWord.equals("で")){
				convertedWord = "て";
			}else if(oneWord.equals("ど")){
				convertedWord = "と";
			}else if(oneWord.equals("ば") || oneWord.equals("ぱ")){
				convertedWord = "は";
			}else if(oneWord.equals("び") || oneWord.equals("ぴ")){
				convertedWord = "ひ";
			}else if(oneWord.equals("ぶ") || oneWord.equals("ぷ")){
				convertedWord = "ふ";
			}else if(oneWord.equals("べ") || oneWord.equals("ぺ")){
				convertedWord = "へ";
			}else if(oneWord.equals("ぼ") || oneWord.equals("ぽ")){
				convertedWord = "ほ";
			}else if(oneWord.equals("ゃ")){
				convertedWord = "や";
			}else if(oneWord.equals("ゅ")){
				convertedWord = "ゆ";
			}else if(oneWord.equals("ょ")){
				convertedWord = "よ";
			}
		}
		return convertedWord;
	}




//	// simple counter
//	private static int count = 0;
//
//	@GET
//	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//	@Path("/count")
//	public Response count() {
//		count++;
//		return Response.status(200).entity("<count>" + count + "</count>").build();
//	}

}
