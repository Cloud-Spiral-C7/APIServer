package jp.kobe_u.cspiral.tincyhat;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/api/ws")
public class WSChat {

	/**
	 * チャット接続者一覧
	 * （スレッドセーフな＝同時書き込みに強いArrayListみたいなもん）
	 */
	private static final Set<Session> sessions = new CopyOnWriteArraySet<Session>();

	// 接続時の処理
	@OnOpen
	public void onOpen(Session session) {
		// チャット接続者一覧に自分を追加
		sessions.add(session);
		System.out.println("open:" + session.toString());
	}

	// 切断時の処理
	@OnClose
	public void onClose(Session session) {
		// チャット接続者一覧から自分を消す
		sessions.remove(session);
	}

	// テキストメッセージ受信時の処理
	@OnMessage
	public void onMessage(String msg) {
		System.out.println("send:" + msg);
		try {
			// チャット接続者一覧の全てにメッセージを送る
			for (Session session : sessions) {
				session.getBasicRemote().sendText(msg);
			}
		} catch (IOException e) {
		}
	}
}