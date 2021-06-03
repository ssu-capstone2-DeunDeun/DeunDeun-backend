package kr.co.deundeun.groopy.domain.club;

import javax.persistence.*;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class ClubLike extends BaseEntity {

    @Column(nullable = false)
    protected boolean isLiked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    protected User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    public ClubLike(Club club, User user){
        this.club = club;
        this.user = user;
        this.isLiked = true;
    }

    public void updateLike(){
        isLiked = !isLiked;
    }
}
