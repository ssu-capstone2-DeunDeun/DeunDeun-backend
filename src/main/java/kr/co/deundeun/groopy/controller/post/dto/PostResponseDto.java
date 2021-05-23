package kr.co.deundeun.groopy.controller.post.dto;

import kr.co.deundeun.groopy.domain.image.Image;
import kr.co.deundeun.groopy.domain.image.PostImage;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private List<String> postImageUrls;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static PostResponseDto of(Post post) {
        return new PostResponseDto(
                post.getId(), post.getTitle(), post.getContent(),
                post.getAuthor(), post.getViewCount(), post.getLikeCount(),
                post.getPostImages().stream()
                        .map(Image::getImageUrl).collect(Collectors.toList()),
                post.getCreatedAt(), post.getModifiedAt()
        );
    }

    public static List<PostResponseDto> listOf(List<Post> posts) {
        if (posts != null)
            return posts.stream().map(PostResponseDto::of)
                    .collect(Collectors.toList());
        return new ArrayList<>();
    }

}
