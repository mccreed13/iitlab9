package com.example.lab9.bot;

import org.fluentd.logger.FluentLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

@Component
public class InitBot extends TelegramLongPollingBot {
    private static final FluentLogger LOG = FluentLogger.getLogger("telegram", "fluentd", 24224);

    @Value("${BOT_USERNAME}")
    private String botUsername;
    @Value("${BOT_TOKEN}")
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long userId = update.getMessage().getFrom().getId();
            String username = update.getMessage().getFrom().getUserName();
            String messageText = update.getMessage().getText();
            Map<String, Object> data = new HashMap<>();
            data.put("UserId", userId);
            data.put("UserName", username);
            data.put("Message", messageText);

            LOG.log("telegram", data);

            String responseText = "Hello " + username;
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            message.setText(responseText);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
