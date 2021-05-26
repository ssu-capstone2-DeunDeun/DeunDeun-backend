package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.controller.clubApply.dto.ApplyRequestDto;
import kr.co.deundeun.groopy.controller.clubApply.dto.ApplyResponseDto;
import kr.co.deundeun.groopy.controller.clubApply.dto.ApplySummaryResponseDto;
import kr.co.deundeun.groopy.dao.ClubApplyAnswerRepository;
import kr.co.deundeun.groopy.dao.ClubApplyRepository;
import kr.co.deundeun.groopy.dao.ClubRecruitRepository;
import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubApply.ClubApplyAnswer;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.BadRequestException;
import kr.co.deundeun.groopy.exception.IdNotFoundException;
import kr.co.deundeun.groopy.exception.LoginException;
import kr.co.deundeun.groopy.helper.ApplyHelper;
import kr.co.deundeun.groopy.helper.RecruitHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Transactional
@Service
public class ClubApplyService {

    private final ClubApplyRepository clubApplyRepository;

    private final ClubApplyAnswerRepository clubApplyAnswerRepository;

    private final ClubRecruitRepository clubRecruitRepository;

    public ApplyResponseDto apply(User user, ApplyRequestDto applyRequestDto) {
        if (user.getId() == null) throw new LoginException();
        if (applyRequestDto.getClubRecruitId() == null)
            throw new IdNotFoundException("잘못된 모집 공고 ID 입니다.");

        ClubApply clubApply = clubApplyRepository
                .findByUserAndClubRecruitId(user, applyRequestDto.getClubRecruitId());

        ClubRecruit clubRecruit = RecruitHelper
                .findById(clubRecruitRepository, applyRequestDto.getClubRecruitId());

        if (clubApply == null) {
            ClubApply newClubApply = applyRequestDto.toClubApply(user);
            clubApplyRepository.save(newClubApply);

            List<ClubApplyAnswer> clubApplyAnswers =
                    Arrays.asList(new ClubApplyAnswer[clubRecruit.getQuestionSize()]);
            clubApplyAnswers = clubApplyAnswers.stream()
                    .map(clubApplyAnswer -> ClubApplyAnswer.builder().clubApply(newClubApply).build())
                    .collect(Collectors.toList());
            clubApplyAnswerRepository.saveAll(clubApplyAnswers);
            return ApplyResponseDto.of(newClubApply, clubRecruit);
        }

        return ApplyResponseDto.of(clubApply, clubRecruit);
    }

    @Transactional(readOnly = true)
    public List<ApplySummaryResponseDto> getApplies(User user) {
        if (user.getId() == null) throw new LoginException();
        List<ClubApply> clubApplies = clubApplyRepository.findAllByUser(user);
        List<ClubRecruit> clubRecruits = clubRecruitRepository.findAllById(
                clubApplies.stream()
                        .map(ClubApply::getClubRecruitId)
                        .collect(Collectors.toList()));

        return ApplySummaryResponseDto.listOf(clubApplies, clubRecruits);
    }

    @Transactional(readOnly = true)
    public ApplyResponseDto getApply(Long applyId) {
        ClubApply clubApply = ApplyHelper.findById(clubApplyRepository, applyId);
        ClubRecruit clubRecruit = RecruitHelper.findById(clubRecruitRepository, clubApply.getClubRecruitId());
        return ApplyResponseDto.of(clubApply, clubRecruit);
    }

    public void updateApply(Long applyId, ApplyRequestDto applyRequestDto) {
        ClubApply clubApply = ApplyHelper.findById(clubApplyRepository, applyId);
        ClubRecruit clubRecruit = RecruitHelper
                .findById(clubRecruitRepository, applyRequestDto.getClubRecruitId());

        if (applyRequestDto.getApplyAnswers().size() != clubRecruit.getQuestionSize())
            throw new BadRequestException("작성하지 않은 질문이 있습니다.");

        clubApply.update(applyRequestDto);
        clubApplyRepository.save(clubApply);
    }

    public void deleteApply(Long applyId) {
        clubApplyRepository.deleteById(applyId);
    }
}
