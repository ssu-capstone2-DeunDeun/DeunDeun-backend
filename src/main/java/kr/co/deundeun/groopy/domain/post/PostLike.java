package kr.co.deundeun.groopy.domain.post;

import javax.persistence.*;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class PostLike extends BaseEntity {

    @Column(nullable = false)
    protected boolean isLiked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    protected User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public void updateLike(){
        isLiked = !isLiked;
    }
}
