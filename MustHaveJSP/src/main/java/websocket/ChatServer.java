package websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/ChatingServer")
public class ChatServer {
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen //클라이언트 접속 시 실행
	public void OnOpen(Session session) {
		clients.add(session); //세션 추가
		System.out.println("웹 소켓 연결 "+session.getId()); 
		//session.getId() : 톰캣이 각각의 클라이언트 연결에 대해 부여하는 고유한 식별자(ID)를 반환 -> 0부터 시작하는 숫자 형태의 문자열임
	}
	
	@OnMessage //메세지를 받으면 실행
	public void OnMessage(String message, Session session) throws IOException {
		System.out.println("메세지 전송 "+session.getId()+":"+message);
		synchronized (clients) {
			for(Session client:clients) { //모든 클라이언트에 메세지 전달
				if(!client.equals(session)) { //단, 메세지를 보낸 클라이언트는 제외하고 전달
					client.getBasicRemote().sendText(message);
				}
			}
		}
	}
	
	@OnClose //클라이언트와의 연결이 끊기면 실행
	public void onClose(Session session) {
		clients.remove(session);
		System.out.println("웹소켓 종료 "+session.getId());
	}
	
	@OnError
	public void onError(Throwable e) {
		System.out.println("에러발생");
		e.printStackTrace();
	}
}
