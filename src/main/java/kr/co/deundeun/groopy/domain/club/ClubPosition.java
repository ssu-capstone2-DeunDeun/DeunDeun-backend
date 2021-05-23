package kr.co.deundeun.groopy.domain.club;


import javax.persistence.*;
import kr.co.deundeun.groopy.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class ClubPosition extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    private String positionName;

    @Builder
    public ClubPosition(Club club, String positionName){
        this.club = club;
        this.positionName = positionName;
    }
}
