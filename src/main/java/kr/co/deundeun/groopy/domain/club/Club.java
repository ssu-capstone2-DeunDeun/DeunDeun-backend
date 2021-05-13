package kr.co.deundeun.groopy.domain.club;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.hashtag.ClubHashtag;
import kr.co.deundeun.groopy.domain.image.ClubImage;
import kr.co.deundeun.groopy.domain.image.Image;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Club extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(nullable = false)
    private String clubName;

    private int generation;

    private String introduction;

    private String representImageUrl;

    private String backgroundImageUrl;

    @OneToMany(mappedBy = "club")
    private List<ClubHashtag> clubHashtags;

    @OneToMany(mappedBy = "club")
    private List<ClubImage> clubImages;

    @OneToMany(mappedBy = "club")
    private List<Post> clubPosts;

    @OneToMany(mappedBy = "club")
    private List<ClubRecruit> clubRecruits;

}
