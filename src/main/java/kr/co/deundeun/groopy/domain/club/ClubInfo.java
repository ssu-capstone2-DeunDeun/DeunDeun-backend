package kr.co.deundeun.groopy.domain.club;

import kr.co.deundeun.groopy.domain.BaseEntity;

import javax.persistence.*;

@Entity
public class ClubInfo extends BaseEntity {

    @Column(nullable = false)
    private String clubName;

    private int generation;

    private String introduction;

    @OneToOne
    @MapsId
    private Club club;
}
