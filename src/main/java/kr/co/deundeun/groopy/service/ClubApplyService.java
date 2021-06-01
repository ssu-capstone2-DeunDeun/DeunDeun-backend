package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dao.CommentRepository;
import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.dto.clubApply.ApplyRequestDto;
import kr.co.deundeun.groopy.dto.clubApply.ApplyResponseDto;
import kr.co.deundeun.groopy.dto.clubApply.ApplySummaryResponseDto;
import kr.co.deundeun.groopy.dao.ClubApplyRepository;
import kr.co.deundeun.groopy.dao.ClubRecruitRepository;
import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.IdNotFoundException;
import kr.co.deundeun.groopy.exception.LoginException;
import kr.co.deundeun.groopy.helper.ClubApplyHelper;
import kr.co.deundeun.groopy.helper.ClubRecruitHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ClubApplyService {

    private final ClubApplyRepository clubApplyRepository;
    private final ClubRecruitRepository clubRecruitRepository;
    private final CommentRepository commentRepository;

    public void apply(User user, Long clubRecruitId, ApplyRequestDto applyRequestDto) {

        // TODO 이 부분 Validation @NotNull? 로 처리, 수정해야함.
        if (user.getId() == null) throw new LoginException();
        if (clubRecruitId== null)
            throw new IdNotFoundException("잘못된 모집 공고 ID 입니다.");

        ClubRecruit clubRecruit = ClubRecruitHelper.findById(clubRecruitRepository, clubRecruitId);
        ClubApply clubApply = applyRequestDto.toClubApply(user, clubRecruit);
        clubApplyRepository.save(clubApply);
//        if (clubApply == null) {
//            ClubApply newClubApply = applyRequestDto.toClubApply(user);
//            clubApplyRepository.save(newClubApply);
//
//            List<ClubApplyAnswer> clubApplyAnswers =
//                    Arrays.asList(new ClubApplyAnswer[clubRecruit.getQuestionSize()]);
//            clubApplyAnswers = clubApplyAnswers.stream()
//                    .map(clubApplyAnswer -> ClubApplyAnswer.builder().clubApply(newClubApply).build())
//                    .collect(Collectors.toList());
//            clubApplyAnswerRepository.saveAll(clubApplyAnswers);
//            return ApplyResponseDto.of(newClubApply, clubRecruit);
//        }
//
//        return ApplyResponseDto.of(clubApply, clubRecruit);
    }

    public List<ApplySummaryResponseDto> getApplies(User user) {

        if (user.getId() == null) throw new LoginException();
        List<ClubApply> clubApplies = clubApplyRepository.findAllByUser(user);
        return ApplySummaryResponseDto.listOf(clubApplies);
    }


    public ApplyResponseDto getApply(Long applyId) {
        ClubApply clubApply = ClubApplyHelper.findById(clubApplyRepository, applyId);
        return new ApplyResponseDto(clubApply);
    }

    public void updateApply(Long applyId, ApplyRequestDto applyRequestDto) {
        ClubApply clubApply = ClubApplyHelper.findById(clubApplyRepository, applyId);
        clubApply.updateAnswers(applyRequestDto);
    }

    public void deleteApply(Long applyId) {

        ClubApply clubApply = ClubApplyHelper.findById(clubApplyRepository, applyId);

        List<Comment> comments = clubApply.getComments();
        comments.forEach(Comment::deleteRelationship);
        commentRepository.deleteAll(comments);

        ClubRecruit clubRecruit = clubApply.getClubRecruit();
        clubRecruit.decreaseApplicantCount();

        clubApplyRepository.deleteById(applyId);
    }
}
