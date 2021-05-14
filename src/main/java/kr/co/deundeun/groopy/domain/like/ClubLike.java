package kr.co.deundeun.groopy.domain.like;

import javax.persistence.*;

import kr.co.deundeun.groopy.domain.club.Club;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class ClubLike extends Likes {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Club club;
}
