package kr.co.deundeun.groopy.domain.like;

import javax.persistence.*;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class ClubLike extends Likes {
    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    @Builder
    public ClubLike(Club club, User user){
        this.club = club;
        this.user = user;
        this.isLiked = true;
    }
}
