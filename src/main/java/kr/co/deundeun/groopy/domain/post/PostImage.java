package kr.co.deundeun.groopy.domain.post;

import kr.co.deundeun.groopy.domain.image.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class PostImage extends Image {

    @ManyToOne
    private Post post;
}
