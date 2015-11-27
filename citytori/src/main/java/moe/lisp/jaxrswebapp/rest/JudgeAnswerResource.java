package moe.lisp.jaxrswebapp.rest;

import java.net.URLDecoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import moe.lisp.jaxrswebapp.controller.DBController;
import moe.lisp.jaxrswebapp.entity.QueryAnswer;
import moe.lisp.jaxrswebapp.entity.ResponseJudgement;
import moe.lisp.jaxrswebapp.entity.Room;


@Path("JudgeAnswer")
public class JudgeAnswerResource {

	DBController controller = new DBController();

	@POST
	@Consumes({MediaType.APPLICATION_ATOM_XML,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseJudgement getJudgement(QueryAnswer answer){

		ResponseJudgement judgement = new ResponseJudgement();
		String phonetic = "";
		String locationName = "";
		try{
			phonetic = URLDecoder.decode(answer.getPhonetic(),"utf-8");
			locationName = URLDecoder.decode(answer.getLocationName(),"utf-8");
		}catch (Exception e){

		}

		boolean isCurrentWord = checkCurrentWord(answer.getUserId(), phonetic);
		boolean isNewWord = checkNewWord(answer.getUserId(), phonetic);

		if(isCurrentWord){
			if(isNewWord){
				controller.setCurrentWord(answer.getUserId(), convertSonantMark(phonetic.substring(phonetic.length() - 1)));
				controller.setAnswer(answer.getUserId(), locationName , phonetic);
				if(checkFinish(answer.getUserId())){
					judgement.setJudgeFlag("Finish");
				}else{
					judgement.setJudgeFlag("OK");
				}
			}else{
				judgement.setJudgeFlag("NG:New");
			}
		}else{
			judgement.setJudgeFlag("NG:Current");
		}

		return judgement;

	}

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


	private boolean checkNewWord(String userId, String phonetic){

		long answerNum = controller.getAnswerNum(userId , phonetic);
		if(answerNum == 0){
			return true;
		}else{
			return false;
		}

	}

	private boolean checkFinish(String userId){
		long goal = (long)controller.getWordNum(userId);
		long current = controller.getAnswerNum(userId);
		if((goal - current) == 0){
			return true;
		}else{
			return false;
		}
	}

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
}
