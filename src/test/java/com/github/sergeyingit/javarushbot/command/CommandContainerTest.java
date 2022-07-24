package com.github.sergeyingit.javarushbot.command;

import com.github.sergeyingit.javarushbot.javarushclient.JavaRushGroupClient;
import com.github.sergeyingit.javarushbot.service.GroupSubService;
import com.github.sergeyingit.javarushbot.service.SendBotMessageService;
import com.github.sergeyingit.javarushbot.service.StatisticsService;
import com.github.sergeyingit.javarushbot.service.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;


@DisplayName("Unit-level testing for CommandContainer")
class CommandContainerTest {

    private CommandContainer commandContainer;

    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        JavaRushGroupClient groupClient = Mockito.mock(JavaRushGroupClient.class);
        GroupSubService groupSubService = Mockito.mock(GroupSubService.class);
        StatisticsService statisticsService = Mockito.mock(StatisticsService.class);
        commandContainer = new CommandContainer(sendBotMessageService, telegramUserService, groupClient, groupSubService,singletonList("userId"), statisticsService);
    }

    @Test
    public void shouldGetAllTheExistingCommands() {
        //when-then
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.retrieveCommand(commandName.getCommandName(), "userId");
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand() {
        //given
        String unknownCommand = "/fgjhdfgdfg";

        //when
        Command command = commandContainer.retrieveCommand(unknownCommand, "userId");

        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}