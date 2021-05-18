package kr.co.deundeun.groopy.controller.post.dto;

import kr.co.deundeun.groopy.domain.image.PostImage;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private int viewCount;
    private int likeCount;
    //private List<PostImage> postImages;

    public static PostResponseDto of(Post post){
        return new PostResponseDto(
                post.getId(), post.getTitle(), post.getContent(),
                post.getAuthor(), post.getViewCount(), post.getLikeCount()//, post.getPostImages()
        );
    }

    public static List<PostResponseDto> listOf(List<Post> posts){
        return posts.stream().map(PostResponseDto::of)
                .collect(Collectors.toList());
    }

}
