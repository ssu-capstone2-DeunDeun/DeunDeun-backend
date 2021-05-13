package kr.co.deundeun.groopy.domain.post;

import kr.co.deundeun.groopy.domain.like.Like;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class PostLike extends Like {

    @ManyToOne
    private Post post;

}
