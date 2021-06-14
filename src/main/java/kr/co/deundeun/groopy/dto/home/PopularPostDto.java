package kr.co.deundeun.groopy.dto.home;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PopularPostDto {

  private Long postId;
  private String clubName;
  private int commentCount;
  private int likeCount;
  private int viewCount;
  private LocalDateTime createdAt;
  private String thumbnailImageUrl;
  private String title;
  private String content;

  public PopularPostDto(Post post) {
    this.postId = post.getId();
    this.clubName = post.getClub().getClubName();
    this.commentCount = post.getCommentCount();
    this.likeCount = post.getLikeCount();
    this.createdAt = post.getCreatedAt();
    this.thumbnailImageUrl = post.getThumbnailImageUrl();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.viewCount = post.getViewCount();
  }

  public static List<PopularPostDto> listOf(List<Post> posts) {
    if (posts == null) {
      return new ArrayList<>();
    }
    return posts.stream()
                .map(PopularPostDto::new)
                .collect(Collectors.toList());
  }
}
