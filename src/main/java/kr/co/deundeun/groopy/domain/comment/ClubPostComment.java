package kr.co.deundeun.groopy.domain.comment;


import kr.co.deundeun.groopy.domain.post.ClubPost;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
@Entity
public class ClubPostComment extends Comment{

    @ManyToOne
    private ClubPost clubPost;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubPostComment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<ClubPostComment> childComment;

}
