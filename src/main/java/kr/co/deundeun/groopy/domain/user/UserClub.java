package kr.co.deundeun.groopy.domain.user;

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
public class UserClub extends BaseEntity {

    @ManyToOne
    private UserHistory userHistory;

    @ManyToOne
    private Club club;

    @ManyToOne
    private ClubRecruit clubRecruit;

    @Builder
    public UserClub(UserHistory userHistory, Club club){
        this.userHistory = userHistory;
        this.club = club;
    }

}
