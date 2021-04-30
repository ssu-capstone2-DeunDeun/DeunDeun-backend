package kr.co.deundeun.groopy.domain.club;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.hashtag.ClubHashtag;
import kr.co.deundeun.groopy.domain.image.ClubImage;
import kr.co.deundeun.groopy.domain.like.UserClubLike;
import kr.co.deundeun.groopy.domain.post.ClubPost;
import kr.co.deundeun.groopy.domain.user.UserClub;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Club extends BaseEntity {

    private String clubName;

    private int generation;

    private String introduction;

    @OneToMany(mappedBy = "club")
    private List<UserClub> memberList;

    @OneToMany(mappedBy = "club")
    private List<ClubHashtag> clubHashtagList;

    @OneToMany(mappedBy = "club")
    private List<UserClubLike> userClubLikeList;

    @OneToMany(mappedBy = "club")
    private List<ClubImage> clubImageList;

    @OneToMany(mappedBy = "club")
    private List<ClubPost> clubPostList;

    @OneToMany(mappedBy = "club")
    private List<ClubRecruit> clubRecruitList;

}
