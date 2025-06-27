package com.InnovativePractice;


import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

public class fulx_test  {

    interface Assistant {

        TokenStream chat(String message);
    }

    public static void main(String[] args) {

        // Sorry, "demo" API key does not support streaming (yet). Please use your own key.
        StreamingChatLanguageModel model = OpenAiStreamingChatModel.builder()
                .apiKey("sk-73684582c344412283052730ec878da1")
                .modelName("deepseek-chat")
                .baseUrl("https://api.deepseek.com")
                .build();

        Assistant assistant = AiServices.create(Assistant.class, model);

        TokenStream tokenStream = assistant.chat("Tell me a joke");

        tokenStream.onNext(System.out::println)
                .onComplete(System.out::println)
                .onError(Throwable::printStackTrace)
                .start();
    }
}