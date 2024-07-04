package com.geektrade.geektradebackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public final class OpenAIResponse {

    private String id;
    private String object;
    private Long created;
    private String model;
    private List<OpenAIChoices> choices;
    private Object usage;
    private String fingerprint;

    @Data
    public static final class OpenAIChoices {
        private Integer index;
        private OpenAIMessage message;
        private Object logprobs;
        private String finish_reason;
    }

    @Data
    public static final class OpenAIMessage {
        private String role;
        private String content;
    }
}
