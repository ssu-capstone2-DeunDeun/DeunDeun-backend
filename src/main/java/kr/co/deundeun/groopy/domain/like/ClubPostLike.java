package kr.co.deundeun.groopy.domain.like;

import kr.co.deundeun.groopy.domain.post.ClubPost;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class ClubPostLike extends Like{

    @ManyToOne
    private ClubPost clubPost;

}
