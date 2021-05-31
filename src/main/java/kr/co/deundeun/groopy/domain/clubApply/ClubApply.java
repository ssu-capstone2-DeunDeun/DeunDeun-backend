package kr.co.deundeun.groopy.domain.clubApply;

import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.persistence.*;

import kr.co.deundeun.groopy.dto.clubApply.ApplyRequestDto;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.clubApply.constant.ClubApplyStatus;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.BadRequestException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private int commentCount = 0;

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

    public void updateAnswers(ApplyRequestDto applyRequestDto) {

        List<String> newAnswers = applyRequestDto.getApplyAnswers();
        validateSize(newAnswers);
        for (int i = 0; i < this.clubApplyAnswers.size(); i++) {
            clubApplyAnswers.get(i).updateAnswer(newAnswers.get(i));
        }

    }

    private void validateSize(List<String> newAnswers) {
        if (newAnswers.size() < clubRecruit.getQuestionSize())
            throw new BadRequestException("답변 수가 질문 수 보다 적습니다.");
        if (newAnswers.size() > clubRecruit.getQuestionSize())
            throw new BadRequestException("답변 수가 질문 수 보다 많습니다.");
    }

    public void setClubApplyStatus(ClubApplyStatus clubApplyStatus){
        this.clubApplyStatus = clubApplyStatus;
    }

    public void increaseCommentCount(){
        this.commentCount += 1;
    }

    public void decreaseCommentCount(){
        if (this.commentCount <= 0){
            return;
        }
        this.commentCount -= 1;
    }

}
