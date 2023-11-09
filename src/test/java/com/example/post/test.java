package com.example.post;

import com.example.post.dto.PostRequestDto;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import com.example.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class PostServiceTest {

    @Test
    void updatePostInvalidPassword() {
        // Arrange
        PostRepository postRepository = mock(PostRepository.class);
        PostService postService = new PostService(postRepository);

        Long postId = 1L;
        String incorrectPassword = "incorrectPassword";
        PostRequestDto requestDto = new PostRequestDto(/* initialize with required data */);

        Post existingPost = mock(Post.class); // Use Mockito to create a mock
        when(postRepository.findById(postId)).thenReturn(java.util.Optional.of(existingPost));

        // Act and Assert
        assertThrows(SecurityException.class, () -> {
            postService.updatePost(postId, incorrectPassword, requestDto);
        });

        // Verify that the update method was not called
        verify(existingPost, never()).update(requestDto);
    }

    @Test
    void deletePostInvalidPassword() {
        // Arrange
        PostRepository postRepository = mock(PostRepository.class);
        PostService postService = new PostService(postRepository);

        Long postId = 1L;
        String incorrectPassword = "incorrectPassword";

        Post existingPost = mock(Post.class); // Use Mockito to create a mock
        when(postRepository.findById(postId)).thenReturn(java.util.Optional.of(existingPost));

        // Act and Assert
        assertThrows(SecurityException.class, () -> {
            postService.deletePost(postId, incorrectPassword);
        });

        // Verify that the delete method was not called
        verify(postRepository, never()).delete(existingPost);
    }
}