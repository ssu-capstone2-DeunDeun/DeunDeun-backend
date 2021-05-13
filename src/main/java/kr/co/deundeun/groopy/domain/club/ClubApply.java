package kr.co.deundeun.groopy.domain.club;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.comment.ClubApplyComment;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ClubApply extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private ClubRecruit clubRecruit;

    private ClubApplyStatus clubApplyStatus;

    @OneToMany(mappedBy = "clubApply")
    private List<ClubApplyAnswer> clubApplyAnswers;

    @OneToMany(mappedBy = "clubApply")
    private  List<ClubApplyComment> clubApplyComments;

}
