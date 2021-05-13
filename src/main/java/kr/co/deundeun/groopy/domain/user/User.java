package kr.co.deundeun.groopy.domain.user;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import kr.co.deundeun.groopy.config.security.oauth2.SocialProviderType;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.hashtag.UserHashtag;
import kr.co.deundeun.groopy.domain.like.ClubLike;
import kr.co.deundeun.groopy.domain.like.PostLike;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@NoArgsConstructor
@Getter
@Entity
public class User extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialProviderType socialProvider;

    @Column(nullable = false)
    private String socialId;

    private String jwtRefreshToken;

    private String name;

    private String nickname;

    private String phoneNumber;

    @Email
    @Column(nullable = false)
    private String email;

    private String userImageUrl;

    @OneToMany(mappedBy = "user")
    private Set<Participate> participates = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserHashtag> userHashtags = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<ClubLike> clubLikes = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<PostLike> postLikes = new HashSet<>();

}
