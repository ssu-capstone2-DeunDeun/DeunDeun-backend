package kr.co.deundeun.groopy.domain.like;

import javax.persistence.*;

import kr.co.deundeun.groopy.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class PostLike extends Likes {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Post post;
}
