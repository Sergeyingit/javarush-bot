package com.github.sergeyingit.javarushbot.command;

import com.github.sergeyingit.javarushbot.command.annotation.AdminCommand;
import com.github.sergeyingit.javarushbot.javarushclient.JavaRushGroupClient;
import com.github.sergeyingit.javarushbot.service.GroupSubService;
import com.github.sergeyingit.javarushbot.service.SendBotMessageService;
import com.github.sergeyingit.javarushbot.service.StatisticsService;
import com.github.sergeyingit.javarushbot.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;

import java.util.List;

import static com.github.sergeyingit.javarushbot.command.CommandName.*;
import static java.util.Objects.nonNull;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;
    private final List<String> admins;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, JavaRushGroupClient javaRushGroupClient, GroupSubService groupSubService, List<String> admins, StatisticsService statisticsService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, statisticsService))
                .put(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(sendBotMessageService, javaRushGroupClient, groupSubService))
                .put(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(sendBotMessageService, telegramUserService))
                .put(DELETE_GROUP_SUB.getCommandName(), new DeleteGroupSubCommand(sendBotMessageService, groupSubService, telegramUserService))
                .put(ADMIN_HELP.getCommandName(), new AdminHelpCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
        this.admins = admins;
    }

    public Command findCommand(String commandIdentifier, String userId) {
        Command orDefault = commandMap.getOrDefault(commandIdentifier, unknownCommand);
        if (isAdminCommand(orDefault)) {
            if (admins.contains(userId)) {
                return orDefault;
            } else {
                return unknownCommand;
            }
        }
        return orDefault;
    }

    private boolean isAdminCommand(Command command) {
        return nonNull(command.getClass().getAnnotation(AdminCommand.class));
    }
}
