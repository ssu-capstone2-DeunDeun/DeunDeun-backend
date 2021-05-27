package kr.co.deundeun.groopy.domain.post;

import javax.persistence.*;

import kr.co.deundeun.groopy.dto.post.PostRequestDto;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Post extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    private String title;

    private String author;

    private String content;

    private String thumbnailImageUrl;

    private int commentCount = 0;

    private int likeCount = 0;

    private int viewCount = 0;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public Post(PostRequestDto postRequestDto){
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.thumbnailImageUrl = postRequestDto.getThumbnailUrl();
    }

    @Builder
    public Post(Club club, String author, PostRequestDto postRequestDto) {
        this(postRequestDto);
        this.club = club;
        this.author = author;
    }

    public void increaseViewCount() {
        viewCount++;
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }

    public void increaseLikeCount() {
        likeCount++;
    }

    public void decreaseLikeCount() {
        if (likeCount > 0) likeCount--;
        else likeCount = 0;
    }

}
