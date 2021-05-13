package kr.co.deundeun.groopy.domain.club;


import javax.persistence.*;
import kr.co.deundeun.groopy.domain.BaseEntity;

@Entity
public class ClubPosition extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    private String positionName;
}
