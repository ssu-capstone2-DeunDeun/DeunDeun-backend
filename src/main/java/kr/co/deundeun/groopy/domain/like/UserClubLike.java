package kr.co.deundeun.groopy.domain.like;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class UserClubLike extends Like {

    @ManyToOne
    private User user;

    @ManyToOne
    private Club club;

}
