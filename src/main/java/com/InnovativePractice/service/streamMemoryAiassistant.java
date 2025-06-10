package com.InnovativePractice.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class streamMemoryAiassistant {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String URL = "http://10.142.164.246:5000/api/agent";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(50, TimeUnit.SECONDS)
            .readTimeout(200, TimeUnit.SECONDS)
            .build();

    public String chat(String message) throws IOException {
        String jsonBody = "{\"question\":\"" + message + "\"}";
        RequestBody body = RequestBody.create(jsonBody, JSON);

        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        return "test";
        /*try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IOException("Response body is null");
            }

            String responseText = responseBody.string();
            JsonNode root = objectMapper.readTree(responseText);
            return root.path("response").asText();
        }*/
    }
}