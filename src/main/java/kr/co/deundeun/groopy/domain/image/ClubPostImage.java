package kr.co.deundeun.groopy.domain.image;

import kr.co.deundeun.groopy.domain.post.ClubPost;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class ClubPostImage extends Image{

    @ManyToOne
    private ClubPost clubPost;
}
