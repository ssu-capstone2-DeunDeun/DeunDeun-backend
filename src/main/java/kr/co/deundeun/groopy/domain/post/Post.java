package kr.co.deundeun.groopy.domain.post;

import javax.persistence.FetchType;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.image.Image;
import kr.co.deundeun.groopy.domain.image.PostImage;
import kr.co.deundeun.groopy.domain.user.User;
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

    private String content;

    @OneToMany(mappedBy = "clubPost")
    private List<PostImage> postImages;

    @OneToMany(mappedBy = "clubPost")
    private List<Comment> comments;
}
