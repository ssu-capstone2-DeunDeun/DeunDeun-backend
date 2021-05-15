package kr.co.deundeun.groopy.domain.post;

import javax.persistence.FetchType;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.image.Image;
import kr.co.deundeun.groopy.domain.image.PostImage;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "post")
    private List<PostImage> postImages;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @Builder
    public Post(Club club, String title, String author, String content, List<PostImage> postImages, List<Comment> comments){
        this.club = club;
        this.title = title;
        this.author = author;
        this.content = content;
        this.postImages = postImages;
        this.comments = comments;
    }
}
