package kr.co.deundeun.groopy.domain.comment;

import kr.co.deundeun.groopy.domain.club.ClubApply;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ClubApplyComment extends Comment{

    @ManyToOne
    private ClubApply clubApply;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<Comment> childComment;

}
