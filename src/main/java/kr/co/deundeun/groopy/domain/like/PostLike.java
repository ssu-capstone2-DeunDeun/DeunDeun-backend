package kr.co.deundeun.groopy.domain.like;

import javax.persistence.Column;
import javax.persistence.FetchType;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Getter
@NoArgsConstructor
@Entity
public class PostLike extends Likes {
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Post post;
}
