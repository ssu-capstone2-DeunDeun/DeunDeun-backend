package kr.co.deundeun.groopy.domain.post;

import javax.persistence.*;

import kr.co.deundeun.groopy.controller.post.dto.PostRequestDto;
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

    @Builder
    public Post(Club club, String title, String author, String content, List<Comment> comments) {
        this.club = club;
        this.title = title;
        this.author = author;
        this.content = content;
        this.comments = comments;
    }

    public void increaseViewCount() {
        viewCount++;
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }

}
