package kr.co.deundeun.groopy.domain.user;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MapsId;
import kr.co.deundeun.groopy.config.security.oauth2.SocialProviderType;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.image.UserImage;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class UserInfo {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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

    @OneToOne
    @MapsId
    private User user;
}
