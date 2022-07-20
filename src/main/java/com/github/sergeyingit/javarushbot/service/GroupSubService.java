package com.github.sergeyingit.javarushbot.service;

import com.github.sergeyingit.javarushbot.javarushclient.dto.GroupDiscussionInfo;
import com.github.sergeyingit.javarushbot.repository.entity.GroupSub;

/**
 * Service for manipulating with {@link GroupSub}.
 */
public interface GroupSubService {
    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
}
