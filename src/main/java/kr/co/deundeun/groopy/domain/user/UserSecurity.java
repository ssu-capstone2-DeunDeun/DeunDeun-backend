package kr.co.deundeun.groopy.domain.user;

import kr.co.deundeun.groopy.config.security.oauth2.SocialProviderType;
import kr.co.deundeun.groopy.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@Getter
@Entity
public class UserSecurity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialProviderType socialProvider;

    @Column(nullable = false)
    private String socialId;

    private String jwtRefreshToken;

    @Builder
    public UserSecurity(SocialProviderType socialProvider, String socialId) {
        this.socialProvider = socialProvider;
        this.socialId = socialId;
    }

}
