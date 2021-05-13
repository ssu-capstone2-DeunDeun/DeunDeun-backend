package kr.co.deundeun.groopy.domain.post;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.user.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Post extends BaseEntity {

    @ManyToOne
    private Club club;

    private String title;

    private String content;

    @OneToOne
    private UserInfo author;

    @OneToMany(mappedBy = "clubPost")
    private List<PostImage> postImages;

    @OneToMany(mappedBy = "clubPost")
    private List<PostComment> postComments;
}
