package com.example.book_shop.story;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Story {
    @Null
    private Long storyId;

    @NotNull
    private Long authorId;

    @NotNull
    private String title;
}
