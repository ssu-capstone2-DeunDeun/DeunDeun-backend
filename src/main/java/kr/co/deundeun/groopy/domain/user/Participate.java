package kr.co.deundeun.groopy.domain.user;

import javax.persistence.*;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubPosition;
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
    private Club club;

    @ManyToOne(cascade = CascadeType.ALL)
    private ClubPosition clubPosition;

    private int generation;

    private boolean isAdmin;

    public Participate(User user, Club club, boolean isAdmin) {
        this.user = user;
        this.club = club;
        this.generation = club.getGeneration();
        this.isAdmin = isAdmin;
    }

    public void setClubPosition(ClubPosition clubPosition) {
        this.clubPosition = clubPosition;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
