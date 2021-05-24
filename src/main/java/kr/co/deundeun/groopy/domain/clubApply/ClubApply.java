package kr.co.deundeun.groopy.domain.clubApply;

import javax.persistence.*;

import kr.co.deundeun.groopy.controller.clubApply.dto.ApplyRequestDto;
import kr.co.deundeun.groopy.controller.clubApply.dto.ApplyResponseDto;
import kr.co.deundeun.groopy.controller.clubApply.dto.ApplySummaryResponseDto;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.clubApply.constant.ClubApplyStatus;
import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Entity
public class ClubApply extends BaseEntity {

    @ManyToOne
    private User user;

    private Long clubRecruitId;

    private ClubApplyStatus clubApplyStatus;

    @OneToMany(mappedBy = "clubApply", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy
    private List<ClubApplyAnswer> clubApplyAnswers;

    @OneToMany(mappedBy = "clubApply")
    private List<Comment> comments;

    @Builder
    public ClubApply(User user, Long clubRecruitId, List<ClubApplyAnswer> clubApplyAnswers) {
        this.user = user;
        this.clubRecruitId = clubRecruitId;
        this.clubApplyStatus = ClubApplyStatus.WAITING;
        this.clubApplyAnswers = clubApplyAnswers;
    }

    public void update(ApplyRequestDto applyRequestDto) {
        AtomicInteger index = new AtomicInteger();
        this.clubApplyAnswers.forEach(
            clubApplyAnswer -> clubApplyAnswer.updateAnswer(applyRequestDto
                .getApplyAnswers()
                .get(index.getAndIncrement())));
    }
}
