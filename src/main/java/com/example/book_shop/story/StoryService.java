package com.example.book_shop.story;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StoryService {
    private static final Logger log = LoggerFactory.getLogger(StoryService.class);

    private final StoryRepository storyRepository;
    private final StoryMapper storyMapper;

    public StoryService(StoryRepository storyRepository, StoryMapper storyMapper) {
        this.storyRepository = storyRepository;
        this.storyMapper = storyMapper;
    }


}
