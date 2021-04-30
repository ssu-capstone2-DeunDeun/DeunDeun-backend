package kr.co.deundeun.groopy.domain.user;

import kr.co.deundeun.groopy.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Entity
public class UserSecurity extends BaseEntity {

    private String socialProvider;

    private String socialId;

    private String jwtRefreshToken;

    private String socialRefreshToken;

}
