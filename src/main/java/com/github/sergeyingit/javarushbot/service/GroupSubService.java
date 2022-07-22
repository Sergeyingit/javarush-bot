package com.github.sergeyingit.javarushbot.service;

import com.github.sergeyingit.javarushbot.javarushclient.dto.GroupDiscussionInfo;
import com.github.sergeyingit.javarushbot.repository.entity.GroupSub;

import java.util.Optional;

/**
 * Service for manipulating with {@link GroupSub}.
 */
public interface GroupSubService {
    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
    GroupSub save(GroupSub groupSub);
    Optional<GroupSub> findById(Integer id);
}
