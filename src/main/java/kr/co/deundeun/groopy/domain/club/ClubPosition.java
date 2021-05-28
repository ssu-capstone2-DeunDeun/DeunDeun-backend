package kr.co.deundeun.groopy.domain.club;


import javax.persistence.*;
import kr.co.deundeun.groopy.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class ClubPosition extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    private String positionName;

    public ClubPosition(Club club, String positionName){
        this();
        setClub(club);
        this.positionName = positionName;
    }

    public void setClub(Club club){
        this.club = club;
        club.getClubPositions().add(this);
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
