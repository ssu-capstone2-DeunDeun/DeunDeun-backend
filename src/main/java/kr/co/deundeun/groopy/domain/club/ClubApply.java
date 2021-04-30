package kr.co.deundeun.groopy.domain.club;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.comment.ClubApplyComment;
import kr.co.deundeun.groopy.domain.user.UserHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ClubApply extends BaseEntity {

    @ManyToOne
    private UserHistory userHistory;

    @ManyToOne
    private ClubRecruit clubRecruit;

    private ClubApplyStatus clubApplyStatus;

    @OneToMany(mappedBy = "clubApply")
    private List<ClubApplyAnswer> clubApplyAnswerList;

    @OneToMany(mappedBy = "clubApply")
    private  List<ClubApplyComment> clubApplyCommentList;

}
