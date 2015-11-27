package jp.kobe_u.cspiral.controller;

import java.util.ArrayList;

import jp.kobe_u.cspiral.entity.Answer;
import jp.kobe_u.cspiral.entity.Area;
import jp.kobe_u.cspiral.entity.Ranking;
import jp.kobe_u.cspiral.entity.Room;
import jp.kobe_u.cspiral.entity.User;
import jp.kobe_u.cspiral.util.CSVUtils;
import jp.kobe_u.cspiral.util.DBUtils;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class DBController {
	private final String USERS_COLLECTION_NAME = "users";
	private final String ROOMS_COLLECTION_NAME = "rooms";
	private final String ANSWERS_COLLECTION_NAME = "answers";
	private final String AREAS_COLLECTION_NAME = "areas";
	private final String RANKING_COLLECTION_NAME = "ranking";

	public static final String ROOM_STATUS_CREATE = "Create";
	public static final String ROOM_STATUS_PLAYING = "Playing";
	public static final String ROOM_STATUS_RESULT = "Result";

	public static final String GAME_MODE_TIME = "Time";
	public static final String GAME_MODE_SCORE = "Score";

	private DBCollection usersCollection;
	private DBCollection roomsCollection;
	private DBCollection answersCollection;
	private DBCollection areasCollection;
	private DBCollection rankingCollection;


	private CSVUtils csvUtils = new CSVUtils();

	public DBController(){
		this.usersCollection = DBUtils.getInstance().getDb().getCollection(USERS_COLLECTION_NAME);
		this.roomsCollection = DBUtils.getInstance().getDb().getCollection(ROOMS_COLLECTION_NAME);
		this.answersCollection = DBUtils.getInstance().getDb().getCollection(ANSWERS_COLLECTION_NAME);
		this.areasCollection = DBUtils.getInstance().getDb().getCollection(AREAS_COLLECTION_NAME);
		this.rankingCollection = DBUtils.getInstance().getDb().getCollection(RANKING_COLLECTION_NAME);
	}

	//---------------------usersコレクション-------------------------
	public String getUserId(String userName){
		DBObject query = new BasicDBObject();
		query.put("name", userName);
		DBObject result = usersCollection.findOne(query);
		ObjectId id = (ObjectId)result.get("_id");
		String userId = id.toString();

		return userId;

	}

	public boolean setUserName(String userName){
		DBObject query = new BasicDBObject();
		query.put("name", userName);

		if(usersCollection.find(query).count() == 0){
			query.put("roomId" , "0");
			usersCollection.insert(query);
			return true;
		}

		return false;

	}

	public String getUserName(String userId){
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(userId));
		DBObject result = usersCollection.findOne(query);
		String name = (String)result.get("name");
		System.out.println("Request to get name,userId:" + userId +",name:" + name);

		return name;
	}

	public String getRoomId(String userId){
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(userId));
		DBObject result = usersCollection.findOne(query);
		String roomId = (String)result.get("roomId");
		System.out.println("Request to get roomId,userId:" + userId +",roomId:" + roomId);

		return roomId;
	}

	public void setRoomId(String userId, String roomId){
		DBObject query = new BasicDBObject();
		query.put("_id" , new ObjectId(userId));
		DBObject setOption = new BasicDBObject();
		setOption.put("roomId", roomId);
		DBObject set = new BasicDBObject("$set",setOption);
		roomsCollection.update(query, set);
	}
	//-----------------------------------------------------------

	//---------------------roomsコレクション-------------------------
	public String setRoom(String userId , String name , String gameMode , int wordNum , int limitTime){
		if(gameMode.equals(GAME_MODE_SCORE)){
			wordNum = 0;
		}else if(gameMode.equals(GAME_MODE_TIME)){
			limitTime = 5;
		}else{
			return "Error";
		}

		DBObject query = new BasicDBObject();
		query.put("name", name);
		query.put("gameMode", gameMode);
		query.put("status", ROOM_STATUS_CREATE);
		query.put("currentWord", "0");
		query.put("wordNum",wordNum);
		query.put("limitTime", limitTime);
		query.put("userId", userId);
		roomsCollection.insert(query);

		DBObject result = roomsCollection.findOne(query);
		ObjectId objectId = (ObjectId)result.get("_id");
		String roomId = objectId.toString();

		setRoomId(userId, roomId);

		return roomId;
	}

	public String getGameMode(String roomId){
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(roomId));
		DBObject result = roomsCollection.findOne(query);
		String gameMode = (String)result.get("gameMode");
		System.out.println("Request to get gameMode,gameMode:" + gameMode);

		return gameMode;
	}

	public int getWordNum(String roomId){
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(roomId));
		DBObject result = roomsCollection.findOne(query);
		int wordNum = (Integer)result.get("wordNum");
		System.out.println("Request to get wordNum,wordNum:" + wordNum);

		return wordNum;
	}

	public int getAreaId(String roomId){
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(roomId));
		DBObject result = roomsCollection.findOne(query);
		int areaId = (Integer)result.get("areaId");
		System.out.println("Request to get areaId,areaId:" + areaId);

		return areaId;
	}

	public void setStatus(String roomId, String value){
		DBObject query = new BasicDBObject();
		query.put("_id" , new ObjectId(roomId));
		DBObject setOption = new BasicDBObject();
		setOption.put("status", value);
		DBObject set = new BasicDBObject("$set",setOption);
		roomsCollection.update(query, set);
	}

	public String getCurrentWord(String roomId){
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(roomId));
		DBObject result = roomsCollection.findOne(query);
		String currentWord = (String)result.get("currentWord");
		System.out.println("Request to get currentWord,currentWord:" + currentWord);

		return currentWord;
	}

	public void setCurrentWord(String roomId, String value){
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(roomId));
		DBObject setOption = new BasicDBObject();
		setOption.put("currentWord", value);
		DBObject set = new BasicDBObject("$set",setOption);
		roomsCollection.update(query, set);
	}
	//-----------------------------------------------------------

	//---------------------answersコレクション-------------------------
	public long getAlreadyAnswerNum(String userId , String roomId, String phonetic){
		DBObject query = new BasicDBObject();
		query.put("userId", userId);
		query.put("roomId", roomId);
		query.put("kana", phonetic);

		return answersCollection.count(query);
	}

	public long getAnswerNum(String userId, String roomId){
		DBObject query = new BasicDBObject();
		query.put("userId", userId);
		query.put("roomId", roomId);

		return answersCollection.count(query);
	}

	public void setAnswer(String userId , String roomId, String locationName , String phonetic){
		DBObject query = new BasicDBObject();
		query.put("placeName", locationName);
		query.put("kana", phonetic);
		query.put("userId", userId);
		query.put("roomId", roomId);
		answersCollection.insert(query);
	}
	//-----------------------------------------------------------

	//---------------------areasコレクション-------------------------
//	public String getLatLon(String roomId){
//		DBObject query = new BasicDBObject();
//		query.put("id", getAreaId(roomId));
//		DBObject result = areasCollection.findOne(query);
//		String latLon = ((String)result.get("latLon")).replaceAll(":", ",");
//		System.out.println("Request to get latLon,latLon:" + latLon);
//
//		return latLon;
//	}
	//-----------------------------------------------------------

	//---------------------rankingコレクション-------------------------
	public void setRanking(String gameMode , String name , String score){
		DBObject query = new BasicDBObject();
		query.put("gameMode", gameMode);
		query.put("name", name);
		query.put("score", Double.valueOf(score));
		rankingCollection.insert(query);
	}

	public ArrayList<Ranking> getRanking(String gameMode){

		ArrayList<Ranking> ranking = new ArrayList<Ranking>();

		DBObject query = new BasicDBObject();
		query.put("gameMode", gameMode);
		DBCursor cursor = rankingCollection.find(query);
		DBObject orderBy = new BasicDBObject();
		orderBy.put("score",1);
		cursor.sort(orderBy);
		cursor.limit(10);
		for(DBObject o: cursor){
			Ranking rank = new Ranking();
			rank.setName((String) o.get("name"));
			rank.setScore(((Double) o.get("score")).toString());
//			System.out.println("name:"+rank.getName());
//			System.out.println("score:"+rank.getScore());
			ranking.add(rank);
		}
		for(int i=0;i<ranking.size();i++){
		System.out.println(ranking.get(i).getName());
		System.out.println(ranking.get(i).getScore());
		}
		return ranking;
	}
	//-----------------------------------------------------------
	//-------------------複数コレクション---------------------------

	public Object CloseSingleGame(String roomId) {
		//roomsコレクションから削除
		DBObject roomQuery = new BasicDBObject();
		roomQuery.put("_id",new ObjectId(roomId));
		DBObject room = roomsCollection.findOne(roomQuery);
		System.out.println(room);
		roomsCollection.remove(room);

		//answerコレクションから削除
		DBCursor answers = answersCollection.find(roomQuery);
		for(DBObject o : answers){
			answersCollection.remove(o);
		}

		//該当userデータのroomIdをnullに変更
		DBObject userQuery = new BasicDBObject();
		userQuery.put("roomId",roomId);
		DBObject user = usersCollection.findOne(userQuery);
		user.put("roomId", null);
		usersCollection.save(user);
		return null;
	}
	//---------------------Initializer-------------------------
	public void initializeAll(){
		initializeRooms();
		initializeUsers();
		initializeAnswers();
		initializeAreas();
	}

	public void initializeUsers(){
		usersCollection.drop();

		ArrayList<User> users = new ArrayList<User>();
		users = csvUtils.getUsers();

		ArrayList<String> id = new ArrayList<String>();

		DBCursor cursor = roomsCollection.find();
		for(DBObject o: cursor){
			ObjectId tmp = (ObjectId)o.get("_id");
			id.add(tmp.toString());
		}

		for(int i = 0 ; i < id.size() ; i++){
			DBObject object = new BasicDBObject("name" , users.get(i).getName())
										.append("roomId", id.get(i));
			usersCollection.insert(object);
		}

		DBObject object = new BasicDBObject("name" , users.get(users.size()-1).getName())
									.append("roomId", "0");
		usersCollection.insert(object);
	}

	public void initializeRooms(){
		roomsCollection.drop();

		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms = csvUtils.getRooms();

		for(int i = 0 ; i < rooms.size() ; i++){
			DBObject object = new BasicDBObject("name" , rooms.get(i).getName())
										.append("gameMode", rooms.get(i).getGameMode())
										.append("status", rooms.get(i).getStatus())
										.append("currentWord", rooms.get(i).getCurrentWord())
										.append("wordNum", rooms.get(i).getWordNum())
										.append("limitTime", rooms.get(i).getLimitTime())
										.append("areaId", rooms.get(i).getAreaId());
			roomsCollection.insert(object);
		}
	}

	public void initializeAnswers(){
		answersCollection.drop();

		ArrayList<Answer> answers = new ArrayList<Answer>();
		answers = csvUtils.getAnswers();

		DBObject result = usersCollection.findOne();

		for(int i = 0 ; i < answers.size() ; i++){
			DBObject object = new BasicDBObject("placeName" , answers.get(i).getPlaceName())
										.append("kana", answers.get(i).getKane())
										.append("userId", result.get("_id").toString())
										.append("roomId", result.get("roomId"));
			answersCollection.insert(object);
		}
	}

	public void initializeAreas(){
		areasCollection.drop();

		ArrayList<Area> areas = new ArrayList<Area>();
		areas = csvUtils.getAreas();

		for(int i = 0 ; i < areas.size() ; i++){
			DBObject object = new BasicDBObject("id" , areas.get(i).getId())
										.append("name", areas.get(i).getName())
										.append("latLon", areas.get(i).getLatLon());
			areasCollection.insert(object);
		}
	}

	public void initializeRanking(){
		rankingCollection.drop();

		ArrayList<Ranking> ranking = new ArrayList<Ranking>();
		ranking = csvUtils.getRanking();

		for(int i = 0 ; i < ranking.size() ; i++){
			DBObject object = new BasicDBObject("gameMode" , ranking.get(i).getGameMode())
										.append("name", ranking.get(i).getName())
										.append("score", ranking.get(i).getScore());
			rankingCollection.insert(object);
		}
	}
	//-----------------------------------------------------------

}
