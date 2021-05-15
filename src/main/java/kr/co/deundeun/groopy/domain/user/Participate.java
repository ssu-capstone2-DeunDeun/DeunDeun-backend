package kr.co.deundeun.groopy.domain.user;

import javax.persistence.*;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Participate extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ClubRecruit clubRecruit;

    @Builder
    public Participate(User user, ClubRecruit clubRecruit){
        this.user = user;
        this.clubRecruit = clubRecruit;
    }
}
