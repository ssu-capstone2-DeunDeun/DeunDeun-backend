package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.controller.recruitQuestion.dto.RecruitQuestionRequestDto;
import kr.co.deundeun.groopy.controller.recruitQuestion.dto.RecruitQuestionResponseDto;
import kr.co.deundeun.groopy.dao.ClubRecruitQuestionRepository;
import kr.co.deundeun.groopy.dao.ClubRecruitRepository;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruitQuestion;
import kr.co.deundeun.groopy.helper.RecruitHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecruitQuestionService {

    private final ClubRecruitQuestionRepository clubRecruitQuestionRepository;

    private final ClubRecruitRepository clubRecruitRepository;

    @Transactional
    public void createQuestions(Long recruitId, List<RecruitQuestionRequestDto> recruitQuestionRequestDtos) {
        ClubRecruit clubRecruit = RecruitHelper.findById(clubRecruitRepository, recruitId);
        List<ClubRecruitQuestion> clubRecruitQuestions = recruitQuestionRequestDtos.stream()
                .map(RecruitQuestionRequestDto::toClubRecruitQuestion)
                .collect(Collectors.toList());
        clubRecruitQuestions.forEach(clubRecruitQuestion -> clubRecruitQuestion.setClubRecruit(clubRecruit));
        clubRecruitQuestionRepository.saveAll(clubRecruitQuestions);
    }

    @Transactional(readOnly = true)
    public List<RecruitQuestionResponseDto> getQuestions(Long recruitId) {
        ClubRecruit clubRecruit = RecruitHelper.findById(clubRecruitRepository, recruitId);
        return clubRecruit.getClubApplyForm()
                          .getClubRecruitQuestions()
                          .stream()
                          .map(RecruitQuestionResponseDto::new)
                          .collect(Collectors.toList());
    }

    @Transactional
    public void updateQuestions(Long recruitId, List<RecruitQuestionRequestDto> recruitQuestionRequestDtos) {
        ClubRecruit clubRecruit = RecruitHelper.findById(clubRecruitRepository, recruitId);
        clubRecruitQuestionRepository.deleteAllByClubRecruit(clubRecruit);
        List<ClubRecruitQuestion> clubRecruitQuestions = recruitQuestionRequestDtos.stream()
                .map(RecruitQuestionRequestDto::toClubRecruitQuestion)
                .collect(Collectors.toList());
        clubRecruitQuestions.forEach(clubRecruitQuestion -> clubRecruitQuestion.setClubRecruit(clubRecruit));
        clubRecruitQuestionRepository.saveAll(clubRecruitQuestions);
    }
}
