package kr.co.deundeun.groopy.domain.clubApply;

import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.persistence.*;

import kr.co.deundeun.groopy.controller.clubApply.dto.ApplyRequestDto;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.clubApply.constant.ClubApplyStatus;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubApplyForm;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@Getter
@Entity
public class ClubApply extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubRecruit clubRecruit;

    private ClubApplyStatus clubApplyStatus = ClubApplyStatus.WAITING;

    @OneToMany(mappedBy = "clubApply", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy
    private List<ClubApplyAnswer> clubApplyAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "clubApply")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public ClubApply(User user, ClubRecruit clubRecruit, List<String> clubApplyAnswers) {
        this();
        this.user = user;
        this.clubRecruit = clubRecruit;
        clubRecruit.increaseApplicantCount();
        this.clubApplyAnswers = clubApplyAnswers.stream()
                                                .map(answer -> new ClubApplyAnswer(this, answer))
                                                .collect(Collectors.toList());
    }

    public void update(ApplyRequestDto applyRequestDto) {
        AtomicInteger index = new AtomicInteger();
        this.clubApplyAnswers.forEach(
            clubApplyAnswer -> clubApplyAnswer.updateAnswer(applyRequestDto
                .getApplyAnswers()
                .get(index.getAndIncrement())));
    }
}
