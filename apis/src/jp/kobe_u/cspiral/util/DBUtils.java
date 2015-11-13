package jp.kobe_u.cspiral.util;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * Mongodbのコネクション管理クラス
 * DB接続へのシングルトン管理を行う．
 *
 * @author shin
 * @author kanehira
 *
 */
public class DBUtils {
	public static DBUtils instance = new DBUtils();

	private DB db;

	//private final String host = new String("");
	private final String host = new String("");
	private final String dbName = new String("citytori");

	private DBUtils() {
		try {
			Mongo m = new Mongo(host);
			db = m.getDB(dbName);
		} catch (UnknownHostException | MongoException e) {
			e.printStackTrace();
		}
	}

	public static DBUtils getInstance() {
		return DBUtils.instance;
	}

	public DB getDb() {
		return this.db;
	}

}
