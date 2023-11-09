package com.example.post.dto;

import com.example.post.entity.Post;
import com.example.post.entity.Timestamped;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto extends Timestamped {
        private Long id;
        private String title;
        private String contents;
        private String pw;
        private String username;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.username = post.getUsername();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
