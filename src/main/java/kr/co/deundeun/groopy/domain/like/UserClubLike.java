package kr.co.deundeun.groopy.domain.like;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.user.UserHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class UserClubLike extends Like {

    @ManyToOne
    private UserHistory userHistory;

    @ManyToOne
    private Club club;

}
