package kr.co.kshproject.webDemo.Common;
import org.springframework.stereotype.Component;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;


@Component
public class WebSocketClient extends WebSocketListener {
    //TODO
    private WebSocket webSocket;

    public void connectToWebSocket() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("wss://sydney.bing.com/sydney/ChatHub")
                // 헤더 설정 생략 (필요한 헤더 정보 추가)
                .build();
        webSocket = client.newWebSocket(request, this);
    }

    @Override
    public void onOpen(WebSocket webSocket, okhttp3.Response response) {
        // 서버로 메시지를 전송합니다.
        webSocket.send("Hello, WebSocket Server!");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {

        // 메시지 처리 로직 추가
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {

    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, okhttp3.Response response) {
        t.printStackTrace();
    }
}
