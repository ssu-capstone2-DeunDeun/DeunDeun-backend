package kr.co.deundeun.groopy.domain.like;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@NoArgsConstructor
@Entity
public abstract class Likes extends BaseEntity {

    @Column(nullable = false)
    private boolean isLiked;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private User user;
}

