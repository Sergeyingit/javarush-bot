package com.github.sergeyingit.javarushbot.service;

/**
 * Service for finding new posts.
 */
public interface FindNewPostsService {
    /**
     * Find new posts and notify subscribers about it.
     */
    void findNewPosts();
}
