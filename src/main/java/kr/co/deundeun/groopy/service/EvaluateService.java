package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dao.ClubApplyRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.dao.ParticipateRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubApply.constant.ClubApplyStatus;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.dto.common.page.PageRequestDto;
import kr.co.deundeun.groopy.dto.evaluate.ClubApplyStatusDto;
import kr.co.deundeun.groopy.dto.evaluate.EvaluateRequestDto;
import kr.co.deundeun.groopy.dto.evaluate.EvaluateResponseDto;
import kr.co.deundeun.groopy.helper.ClubApplyHelper;
import kr.co.deundeun.groopy.helper.ClubHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class EvaluateService {

    private final ClubApplyRepository clubApplyRepository;

    private final ParticipateRepository participateRepository;

    private final ClubRepository clubRepository;

    public void evaluateFirstClubApply(EvaluateRequestDto evaluateRequestDto) {
        List<ClubApply> clubApplies = clubApplyRepository.findAllByIdIn(evaluateRequestDto.getClubApplyIds());
        if (evaluateRequestDto.isPass())
            clubApplies.forEach(clubApply -> clubApply.setClubApplyStatus(ClubApplyStatus.FIRST_ROUND_PASS));
        else
            clubApplies.forEach(clubApply -> clubApply.setClubApplyStatus(ClubApplyStatus.FIRST_ROUND_FAIL));
    }

    public void evaluateFinalClubApply(EvaluateRequestDto evaluateRequestDto) {
        List<ClubApply> clubApplies = clubApplyRepository.findAllByIdIn(evaluateRequestDto.getClubApplyIds());
        if (evaluateRequestDto.isPass()) {
            clubApplies.forEach(clubApply -> {
                clubApply.setClubApplyStatus(ClubApplyStatus.FINAL_PASS);
                Participate participate =
                        new Participate(clubApply.getUser(), clubApply.getClubRecruit().getClub(), false);
                participateRepository.save(participate);
            });
        } else
            clubApplies.forEach(clubApply -> clubApply.setClubApplyStatus(ClubApplyStatus.FINAL_FAIL));
    }

    public Page<EvaluateResponseDto> getEvaluations(String clubName, PageRequestDto pageRequestDto) {
        Club club = ClubHelper.findByClubName(clubRepository, clubName);
        return clubApplyRepository.findAllByClubRecruit_Club(club, pageRequestDto.of()).map(EvaluateResponseDto::new);
    }

    public EvaluateResponseDto getEvaluation(Long clubApplyId) {
        ClubApply clubApply = ClubApplyHelper.findById(clubApplyRepository, clubApplyId);
        return new EvaluateResponseDto(clubApply);
    }

}
