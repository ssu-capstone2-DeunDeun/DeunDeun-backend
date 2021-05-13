package kr.co.deundeun.groopy.domain.post;

import javax.persistence.OneToOne;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.comment.ClubPostComment;
import kr.co.deundeun.groopy.domain.image.ClubPostImage;
import kr.co.deundeun.groopy.domain.user.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ClubPost extends BaseEntity {

    @ManyToOne
    private Club club;

    private String title;

    private String content;

    @OneToOne
    private UserInfo author;

    @OneToMany(mappedBy = "clubPost")
    private List<ClubPostImage> clubPostImageList;

    @OneToMany(mappedBy = "clubPost")
    private List<ClubPostComment> clubPostCommentList;
}
