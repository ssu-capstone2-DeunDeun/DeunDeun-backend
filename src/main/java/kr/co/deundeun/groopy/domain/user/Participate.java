package kr.co.deundeun.groopy.domain.user;

import javax.persistence.*;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.ClubPosition;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Participate extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ClubRecruit clubRecruit;

    @ManyToOne(cascade = CascadeType.ALL)
    private ClubPosition clubPosition;

    private boolean isAdmin;

    @Builder
    public Participate(User user, ClubRecruit clubRecruit, ClubPosition clubPosition, boolean isAdmin) {
        this.user = user;
        this.clubRecruit = clubRecruit;
        this.clubPosition = clubPosition;
        this.isAdmin = isAdmin;
    }

    public void setClubPosition(ClubPosition clubPosition) {
        this.clubPosition = clubPosition;
    }

    public void setAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }
}
