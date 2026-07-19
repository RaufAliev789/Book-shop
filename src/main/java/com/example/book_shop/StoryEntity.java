package com.example.book_shop;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "story")
public class StoryEntity {
    @Id
    @Column(name = "story_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @Column(name = "title")
    private String title;

    public StoryEntity(Long storyId){
        this.storyId = storyId;
    }
}
