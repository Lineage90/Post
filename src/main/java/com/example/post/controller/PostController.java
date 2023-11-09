package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping("/post")
    public List<PostResponseDto> getPost() {
        return postService.getPost();

    }

    @PutMapping("/post/{id}")
    public Long updatePost(@PathVariable Long id, @PathVariable String pw, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, pw, requestDto);
    }

    @DeleteMapping("/post/{id}")
    public Long deletePost(@PathVariable Long id, @PathVariable String pw) {
        return postService.deletePost(id, pw);
    }


}
