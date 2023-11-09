package com.example.post.service;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        // RequestDto -> Entity
        Post post = new Post(requestDto);

        // DB 저장
        Post savePost = postRepository.save(post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    public List<PostResponseDto> getPost() {
        // DB 조회
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    @Transactional
    public Long updatePost(Long id, String pw, PostRequestDto requestDto) {

        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);
        if (!isValidPassword(post, pw)) {
            throw new SecurityException("비밀번호가 올바르지 않습니다");
        }

        post.update(requestDto);

        return id;
    }

    public Long deletePost(Long id, String pw) {

        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);
        if (!isValidPassword(post, pw)) {
            throw new SecurityException("비밀번호가 올바르지 않습니다");
        }

        // memo 삭제
        postRepository.delete(post);

        return id;

    }
    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다")
        );
    }

    private boolean isValidPassword(Post post, String password) {
        // 여기에서 비밀번호 검증 로직을 추가

        return Objects.equals(post.getPw(), password);
    }

}
