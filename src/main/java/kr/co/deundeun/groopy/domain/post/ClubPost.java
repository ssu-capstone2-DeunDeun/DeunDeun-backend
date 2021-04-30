package kr.co.deundeun.groopy.domain.post;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.comment.ClubPostComment;
import kr.co.deundeun.groopy.domain.image.ClubPostImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ClubPost extends Post{

    @ManyToOne
    private Club club;

    @OneToMany(mappedBy = "clubPost")
    private List<ClubPostImage> clubPostImageList;

    @OneToMany(mappedBy = "clubPost")
    private List<ClubPostComment> clubPostCommentList;
}
