package kr.co.deundeun.groopy.domain.like;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import kr.co.deundeun.groopy.domain.club.Club;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class ClubLike extends Likes {
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Club club;
}
