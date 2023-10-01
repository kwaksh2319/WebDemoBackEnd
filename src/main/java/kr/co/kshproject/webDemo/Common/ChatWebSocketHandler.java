package kr.co.kshproject.webDemo.Common;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터 수신된 메시지를 처리하는 로직을 구현합니다.
        String receivedMessage = message.getPayload();
        // 처리된 결과를 필요에 따라 클라이언트에게 보낼 수 있습니다.
        session.sendMessage(new TextMessage("서버에서 받은 메시지: " + receivedMessage));
    }
}
