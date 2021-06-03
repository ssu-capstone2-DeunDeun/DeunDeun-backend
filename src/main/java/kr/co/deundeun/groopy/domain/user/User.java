package kr.co.deundeun.groopy.domain.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;

import kr.co.deundeun.groopy.config.security.oauth2.SocialProviderType;
import kr.co.deundeun.groopy.dao.UserHashtagRepository;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.hashtag.HashtagInfo;
import kr.co.deundeun.groopy.domain.hashtag.UserHashtag;
import kr.co.deundeun.groopy.domain.club.ClubLike;
import kr.co.deundeun.groopy.domain.post.PostLike;
import kr.co.deundeun.groopy.dto.user.UserRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserHashtag> userHashtags = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClubLike> clubLikes = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PostLike> postLikes = new HashSet<>();

    @Builder
    public User(String socialId, SocialProviderType socialProvider, String email, String phoneNumber) {
        this.socialId = socialId;
        this.socialProvider = socialProvider;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void saveSignupInfo(UserRequestDto userRequestDto) {
        this.nickname = userRequestDto.getNickname();
        this.name = userRequestDto.getName();
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserHashtags(UserHashtagRepository userHashtagRepository, List<HashtagInfo> hashtagInfos) {
        userHashtagRepository.deleteAllByUser(this);
        List<UserHashtag> userHashtags = UserHashtag.ofList(this, hashtagInfos);
        this.getUserHashtags().addAll(userHashtags);
        userHashtagRepository.saveAll(userHashtags);
    }

    public boolean isUserLikeClub(Club club) {
        return this.clubLikes.stream()
                .anyMatch(clubLike -> clubLike.getClub().equals(club));
    }

}
