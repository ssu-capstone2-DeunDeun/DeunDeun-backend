package kr.co.deundeun.groopy.domain.like;

import javax.persistence.*;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@NoArgsConstructor
@Entity
public abstract class Likes extends BaseEntity {

    @Column(nullable = false)
    protected boolean isLiked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    protected User user;

    public void updateLike(){
        isLiked = !isLiked;
    }
}

