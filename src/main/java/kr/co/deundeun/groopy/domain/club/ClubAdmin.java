package kr.co.deundeun.groopy.domain.club;

import javax.persistence.*;
import kr.co.deundeun.groopy.domain.BaseEntity;

@Entity
public class ClubAdmin extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    @Column(nullable = false)
    private Long userId;
}
