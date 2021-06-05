package kr.co.deundeun.groopy.dto.post;

import kr.co.deundeun.groopy.domain.post.Post;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
public class PostResponseDto {

    private Long postId;
    private String title;
    private String content;
    private String author;
    private int viewCount;
    private int likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.viewCount = post.getViewCount();
        this.likeCount = post.getLikeCount();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.viewCount = post.getViewCount();
        this.likeCount = post.getLikeCount();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    public static PostResponseDto of(Post post) {
        return new PostResponseDto(post);
    }

    public static List<PostResponseDto> listOf(List<Post> posts) {
        if (posts != null)
            return posts.stream().map(PostResponseDto::of)
                    .collect(Collectors.toList());
        return new ArrayList<>();
    }

}
