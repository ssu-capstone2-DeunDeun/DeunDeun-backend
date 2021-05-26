package kr.co.deundeun.groopy.service;

import java.util.List;
import kr.co.deundeun.groopy.controller.clubApplyForm.dto.ApplyFormRequestDto;
import kr.co.deundeun.groopy.controller.clubApplyForm.dto.ApplyFormResponseDto;
import kr.co.deundeun.groopy.dao.ClubApplyFormRepository;
import kr.co.deundeun.groopy.dao.ClubRecruitRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubApplyForm;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.exception.BadRequestException;
import kr.co.deundeun.groopy.helper.ClubApplyFormHelper;
import kr.co.deundeun.groopy.helper.ClubHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RequiredArgsConstructor
@Service
public class ClubApplyFormService {

  private final ClubRepository clubRepository;
  private final ClubApplyFormRepository clubApplyFormRepository;
  private final ClubRecruitRepository clubRecruitRepository;

  // 생성만 있어서 Transactional 필요 X
  public void addClubApplyForm(String clubName, ApplyFormRequestDto applyFormRequestDto) {
    Club club = ClubHelper.findByClubName(clubRepository, clubName);
    ClubApplyForm clubApplyForm = new ClubApplyForm(club, applyFormRequestDto);
    clubApplyFormRepository.save(clubApplyForm);
  }

  public List<ApplyFormResponseDto> getApplyForms(String clubName) {
    Club club = ClubHelper.findByClubName(clubRepository, clubName);
    List<ClubApplyForm> clubApplyForms = club.getClubApplyForms();
    return ApplyFormResponseDto.listOf(clubApplyForms);
  }
  
  @Transactional
  public void deleteClubApplyForm(Long clubApplyFormId) {
    List<ClubRecruit> clubRecruits = clubRecruitRepository.findAllByClubApplyFormId(clubApplyFormId);
    boolean hasApplicantToClubApplyForm = clubRecruits.stream()
                                                      .anyMatch(ClubRecruit::hasApplicant);
    if (hasApplicantToClubApplyForm){
      throw new BadRequestException("해당 지원 양식을 사용하는 모집 공고에 지원자가 존재합니다.");
    }
    ClubApplyForm clubApplyForm = ClubApplyFormHelper.findClubApplyFormId(clubApplyFormRepository, clubApplyFormId);
    clubApplyFormRepository.delete(clubApplyForm);
  }
}
