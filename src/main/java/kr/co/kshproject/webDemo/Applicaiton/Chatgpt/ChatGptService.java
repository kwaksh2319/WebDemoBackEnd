package kr.co.kshproject.webDemo.Applicaiton.Chatgpt;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
@Service
public class ChatGptService {
    private  RestTemplate restTemplate;
    private final String chatGptApiUrl = "https://api.openai.com/v1/chat/completions";

    public ChatGptService() {
        this.restTemplate = new RestTemplate();
    }

    public String getChatResponse(String userInput,String apiKey ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String requestBody = "{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"system\",\"content\":\"" + userInput + "\"}]}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // ChatGPT API 호출
        ResponseEntity<String> responseEntity=null;
        try{
           responseEntity = restTemplate.exchange(chatGptApiUrl, HttpMethod.POST, requestEntity, String.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        // ChatGPT API 응답 처리
        String chatResponse = responseEntity.getBody();

        return chatResponse;
    }
}
