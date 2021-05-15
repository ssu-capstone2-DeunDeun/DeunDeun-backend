package kr.co.deundeun.groopy.controller.post.dto;

import kr.co.deundeun.groopy.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponseDto {

    private String title;
    private String content;
    private String author;

    public static PostResponseDto of(Post post){
        return new PostResponseDto(
                post.getTitle(), post.getContent(), post.getAuthor()
        );

    }

}
