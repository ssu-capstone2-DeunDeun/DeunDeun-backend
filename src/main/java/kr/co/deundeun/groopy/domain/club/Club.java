package kr.co.deundeun.groopy.domain.club;

import kr.co.deundeun.groopy.domain.hashtag.ClubHashtag;
import kr.co.deundeun.groopy.domain.image.ClubImage;
import kr.co.deundeun.groopy.domain.like.UserClubLike;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.domain.user.UserClub;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Club  {

    @Id
    private Long id;

    @OneToMany(mappedBy = "club")
    private List<UserClub> userClubs;

    @OneToMany(mappedBy = "club")
    private List<ClubHashtag> clubHashtags;

    @OneToMany(mappedBy = "club")
    private List<ClubImage> clubImages;

    @OneToMany(mappedBy = "club")
    private List<Post> clubPosts;

    @OneToMany(mappedBy = "club")
    private List<ClubRecruit> clubRecruits;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

}
