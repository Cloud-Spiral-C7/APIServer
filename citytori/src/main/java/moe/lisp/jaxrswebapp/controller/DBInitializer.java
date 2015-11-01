package moe.lisp.jaxrswebapp.controller;

public class DBInitializer {

	public static void main(String args[]){
		DBController controller = new DBController();
		controller.initializeAll();
//		controller.setRanking(controller.getGameMode("5633128d4de93496d452bf61") ,  controller.getUserName("5633128d4de93496d452bf61"), "1");
//		controller.getRanking(controller.getGameMode("5633128d4de93496d452bf61"));
//		controller.CloseSingleGame("56360cbe4de935b29b214f24", controller.getRoomId("56360cbe4de935b29b214f24"));

		//controller.getWordNum("562dbad4922caacefbfbc77c");
//		controller.setStatus("562dbad4922caacefbfbc77c" , DBController.ROOM_STATUS_CREATE);
		//controller.initializeAnswers();
		//controller.setCurrentWord("56305fb8922c1e1f04c9ca10" , "わ");


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
