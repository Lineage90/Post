package com.example.post.dto;

import com.example.post.entity.Post;
import com.example.post.entity.Timestamped;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRequestDto {
    private Long id;
    private String title;
    private String contents;
    private String pw;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
