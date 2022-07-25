package com.github.sergeyingit.javarushbot.command;

import com.github.sergeyingit.javarushbot.service.SendBotMessageService;
import com.github.sergeyingit.javarushbot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.sergeyingit.javarushbot.command.CommandUtils.getChatId;

/**
 * Stop {@link Command}.
 */
public class StopCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String STOP_MESSAGE = "Деактивировал все ваши подписки \uD83D\uDE1F.";

    public StopCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getChatId(update), STOP_MESSAGE);

        telegramUserService.findByChatId(getChatId(update))
                .ifPresent(it -> {
                    it.setActive(false);
                    telegramUserService.save(it);
                });
    }
}
