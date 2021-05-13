package kr.co.deundeun.groopy.domain.user;

import javax.persistence.Column;
import javax.persistence.FetchType;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
public class Participate extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private ClubRecruit clubRecruit;
}
