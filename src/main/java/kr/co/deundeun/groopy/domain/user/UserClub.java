package kr.co.deundeun.groopy.domain.user;

import javax.persistence.FetchType;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubRecruit;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UserClub extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubRecruit clubRecruit;

    @Builder
    public UserClub(User user, Club club){
        this.user = user;
        this.club = club;
    }

}
