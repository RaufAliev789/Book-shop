package com.example.book_shop.story;

import com.example.book_shop.author.AuthorEntity;
import org.springframework.stereotype.Component;

@Component
public class StoryMapper {
    public Story toDomain(StoryEntity story){
        return new Story(
                story.getStoryId(),
                story.getAuthor().getAuthorId(),
                story.getTitle()
        );
    }

    public StoryEntity toEntity(Story story){
        return new StoryEntity(
                story.getStoryId(),
                story.getAuthorId() != null ? new AuthorEntity(story.getAuthorId()) : null, //конструкор в родителе
                story.getTitle()
        );
    }
}
