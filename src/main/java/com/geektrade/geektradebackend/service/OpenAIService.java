package com.geektrade.geektradebackend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geektrade.geektradebackend.config.OpenAIProperties;
import com.geektrade.geektradebackend.dto.OpenAIResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OpenAIService {

    private final static int    MAX_TOKENS = 300;
    private final static String AI_MODEL = "gpt-4o";
    private final static String PROMPT = "I want to create a description for a product that I am selling on a marketplace. Generate a description with relevant details for a listing using easy to understand language. The summary should be short and concise. Do not format the text. Here is the image of the product.";

    private final OkHttpClient client = new OkHttpClient();
    private final OpenAIProperties openAIProperties;

    @SneakyThrows
    public Optional<String> generateDescriptionFrom(String base64Image) {
        var mediaType = MediaType.parse("application/json; charset=utf-8");

        var contentMap = new HashMap<>();
        contentMap.put("type", "text");
        contentMap.put("text", PROMPT);

        var imageMap = new HashMap<>();
        imageMap.put("type", "image_url");

        var imageUrlMap = new HashMap<>();
        imageUrlMap.put("url", "data:image/jpeg;base64," + base64Image);
        imageMap.put("image_url", imageUrlMap);

        var messageMap = new HashMap<>();
        messageMap.put("role", "user");
        messageMap.put("content", new Object[]{contentMap, imageMap});

        var payloadMap = new HashMap<>();
        payloadMap.put("model", AI_MODEL);
        payloadMap.put("messages", new Object[]{messageMap});
        payloadMap.put("max_tokens", MAX_TOKENS);

        var objectMapper = new ObjectMapper();
        var payload = objectMapper.writeValueAsString(payloadMap);

        var body = RequestBody.create(payload, mediaType);
        var request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .post(body)
                .addHeader("Authorization", "Bearer " + openAIProperties.getApiKey())
                .build();

        try (var response = client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null)
                throw new RuntimeException("");

            var responseObj = objectMapper.readValue(response.body().string(), OpenAIResponse.class);
            return Optional.of(responseObj.getChoices().stream().findFirst().orElseThrow().getMessage().getContent());
        }
    }
}
