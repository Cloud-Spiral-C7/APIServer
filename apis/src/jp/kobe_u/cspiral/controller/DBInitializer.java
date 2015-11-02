package jp.kobe_u.cspiral.controller;

public class DBInitializer {

	public static void main(String args[]){
		String userId = "5636ffb11d1f2c925c743fa9";
		String currentWord = "わ";

		DBController controller = new DBController();

		controller.initializeAnswers();
		controller.setCurrentWord(userId , currentWord);


		//		controller.initializeAll();
		//controller.getWordNum("562dbad4922caacefbfbc77c");
//		controller.setStatus("562dbad4922caacefbfbc77c" , DBController.ROOM_STATUS_CREATE);
//		controller.initializeAnswers();
//		controller.setCurrentWord("56305fb8922c1e1f04c9ca10" , "わ");


//		String phonetic = "さっぽろ";
//
//		String lastChar = phonetic.substring(0,1);
//		String currentWord = controller.getCurrentWord("562dbad4922caacefbfbc77c");
//		if(lastChar.equals(currentWord)){
//			System.out.print("OK");
//			//return true;
//		}else{
//			System.out.print("NG");
			//return false;
//		}

//		long answerNum = controller.getAnswerNum("562dbad4922caacefbfbc77c" , phonetic);
//		if(answerNum == 0){
//			System.out.print("OK");
////			//return true;
//		}else{
//			System.out.print("NG");
////			//return true;
//		}
	}

}
