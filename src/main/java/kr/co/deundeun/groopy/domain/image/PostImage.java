package kr.co.deundeun.groopy.domain.image;

import javax.persistence.FetchType;
import kr.co.deundeun.groopy.domain.image.Image;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class PostImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
