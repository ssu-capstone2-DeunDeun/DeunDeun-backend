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
import kr.co.deundeun.groopy.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class ClubApplyService {

    private final ClubApplyRepository clubApplyRepository;

    private final ClubApplyAnswerRepository clubApplyAnswerRepository;

    private final ClubRecruitRepository clubRecruitRepository;

    @Transactional
    public void apply(User user, ApplyRequestDto applyRequestDto){
        ClubApply clubApply = applyRequestDto.toClubApply(user);
        clubApplyRepository.save(clubApply);

        List<ClubApplyAnswer> clubApplyAnswers = applyRequestDto.getApplyAnswers().stream()
                .map(answer -> answer.toClubApplyAnswer(clubApply)).collect(Collectors.toList());
        clubApplyAnswerRepository.saveAll(clubApplyAnswers);
    }

    @Transactional(readOnly = true)
    public List<ApplySummaryResponseDto> getApplies(User user){
        List<ClubApply> clubApplies = clubApplyRepository.findAllByUser(user);
        List<ClubRecruit> clubRecruits = clubRecruitRepository.findAllById(
                clubApplies.stream().map(ClubApply::getClubRecruitId).collect(Collectors.toList()));

        return ApplySummaryResponseDto.listOf(clubApplies, clubRecruits);
    }

    @Transactional(readOnly = true)
    public ApplyResponseDto getApply(Long applyId) {
        ClubApply clubApply = clubApplyRepository.findById(applyId).orElseThrow(() -> new IdNotFoundException("조회하려는 지원서가 존재하지 않습니다."));
        return ApplyResponseDto.of(clubApply);
    }

    @Transactional
    public void changeApply(Long applyId, ApplyRequestDto applyRequestDto) {
        ClubApply clubApply = clubApplyRepository.findById(applyId).orElseThrow(() -> new IdNotFoundException("조회하려는 지원서가 존재하지 않습니다."));
        clubApply.update(applyRequestDto);
        clubApplyRepository.save(clubApply);
    }

    @Transactional
    public void deleteApply(Long applyId) {
        clubApplyRepository.deleteById(applyId);
    }
}
