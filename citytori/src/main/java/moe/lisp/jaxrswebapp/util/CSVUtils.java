package moe.lisp.jaxrswebapp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import moe.lisp.jaxrswebapp.entity.Answer;
import moe.lisp.jaxrswebapp.entity.Area;
import moe.lisp.jaxrswebapp.entity.Ranking;
import moe.lisp.jaxrswebapp.entity.Room;
import moe.lisp.jaxrswebapp.entity.User;

public class CSVUtils {

	private static final String FILE_PATH = ".\\src\\main\\java\\moe\\lisp\\jaxrswebapp\\util\\";
	private static final String FILE_NAME_USERS = "users.csv";
	private static final String FILE_NAME_ROOMS = "rooms.csv";
	private static final String FILE_NAME_ANSWERS = "answers.csv";
	private static final String FILE_NAME_AREAS = "areas.csv";
	private static final String FILE_NAME_RANKING = "ranking.csv";

	public CSVUtils(){

	}

	public ArrayList<User> getUsers(){

		ArrayList<User> users = new ArrayList<User>();


		try {

			File fReader = new File(FILE_PATH + FILE_NAME_USERS);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fReader),"SJIS"));

			String line;

			line = bReader.readLine();
			System.out.println(line);
			while((line = bReader.readLine()) != null){
				User tmpUsers = new User();
				System.out.println(line);
				String[] items = line.split(",");
				tmpUsers.setName(items[0]);
				tmpUsers.setRoomId(items[1]);
				users.add(tmpUsers);
			}

		} catch (Exception e) {
			System.out.println("Error:" + e.toString());
		}

		return users;
	}

	public ArrayList<Room> getRooms(){

		ArrayList<Room> rooms = new ArrayList<Room>();


		try {

			File fReader = new File(FILE_PATH + FILE_NAME_ROOMS);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fReader),"SJIS"));

			String line;

			line = bReader.readLine();
			System.out.println(line);
			while((line = bReader.readLine()) != null){
				Room tmpRoom = new Room();
				System.out.println(line);
				String[] items = line.split(",");
				tmpRoom.setName(items[0]);
				tmpRoom.setGameMode(items[1]);
				tmpRoom.setStatus(items[2]);
				tmpRoom.setCurrentWord(items[3]);
				tmpRoom.setWordNum(Integer.parseInt(items[4]));
				tmpRoom.setLimitTime(Integer.parseInt(items[5]));
				tmpRoom.setAreaId(Integer.parseInt(items[6]));
				rooms.add(tmpRoom);
			}

		} catch (Exception e) {
			System.out.println("Error:" + e.toString());
		}

		return rooms;
	}

	public ArrayList<Answer> getAnswers(){

		ArrayList<Answer> answers = new ArrayList<Answer>();


		try {

			File fReader = new File(FILE_PATH + FILE_NAME_ANSWERS);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fReader),"SJIS"));

			String line;

			line = bReader.readLine();
			System.out.println(line);
			while((line = bReader.readLine()) != null){
				Answer tmpAnswer = new Answer();
				System.out.println(line);
				String[] items = line.split(",");
				tmpAnswer.setPlaceName(items[0]);
				tmpAnswer.setKane(items[1]);
				tmpAnswer.setUserId(items[2]);
				tmpAnswer.setRoomId(items[3]);
				answers.add(tmpAnswer);
			}

		} catch (Exception e) {
			System.out.println("Error:" + e.toString());
		}

		return answers;
	}

	public ArrayList<Area> getAreas(){

		ArrayList<Area> areas = new ArrayList<Area>();


		try {

			File fReader = new File(FILE_PATH + FILE_NAME_AREAS);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fReader),"SJIS"));

			String line;

			line = bReader.readLine();
			System.out.println(line);
			while((line = bReader.readLine()) != null){
				Area tmpArea = new Area();
				System.out.println(line);
				String[] items = line.split(",");
				tmpArea.setId(Integer.parseInt(items[0]));
				tmpArea.setName(items[1]);
				tmpArea.setLatLon(items[2]);
				areas.add(tmpArea);
			}

		} catch (Exception e) {
			System.out.println("Error:" + e.toString());
		}

		return areas;
	}

	public ArrayList<Ranking> getRanking(){

		ArrayList<Ranking> ranking = new ArrayList<Ranking>();

		try {

			File fReader = new File(FILE_PATH + FILE_NAME_RANKING);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fReader),"SJIS"));

			String line;

			line = bReader.readLine();
			System.out.println(line);
			while((line = bReader.readLine()) != null){
				Ranking tmpRanking = new Ranking();
				System.out.println(line);
				String[] items = line.split(",");
				tmpRanking.setGameMode(items[0]);
				tmpRanking.setName(items[1]);
				tmpRanking.setScore(items[2]);
				ranking.add(tmpRanking);
			}

		} catch (Exception e) {
			System.out.println("Error:" + e.toString());
		}

		return ranking;

	}
}
