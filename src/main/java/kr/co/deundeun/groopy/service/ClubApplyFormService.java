package kr.co.deundeun.groopy.service;

import java.util.List;
import kr.co.deundeun.groopy.dto.clubApplyForm.ApplyFormRequestDto;
import kr.co.deundeun.groopy.dto.clubApplyForm.ApplyFormResponseDto;
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

@RequiredArgsConstructor
@Service
public class ClubApplyFormService {

  private final ClubRepository clubRepository;
  private final ClubApplyFormRepository clubApplyFormRepository;
  private final ClubRecruitRepository clubRecruitRepository;

  // 생성만 있어서 Transactional 필요 X
  public void addClubApplyForm(String clubName, ApplyFormRequestDto applyFormRequestDto) {
    Club club = ClubHelper.findByClubName(clubRepository, clubName);
    ClubApplyForm clubApplyForm = applyFormRequestDto.toEntity(club);
    clubApplyFormRepository.save(clubApplyForm);
  }

  public List<ApplyFormResponseDto> getApplyForms(String clubName) {
    Club club = ClubHelper.findByClubName(clubRepository, clubName);
    List<ClubApplyForm> clubApplyForms = club.getClubApplyForms();
    return ApplyFormResponseDto.listOf(clubApplyForms);
  }

  public ApplyFormResponseDto getApplyForm(Long clubApplyFormId) {
    ClubApplyForm clubApplyForm = ClubApplyFormHelper.findByClubApplyFormId(clubApplyFormRepository, clubApplyFormId);
    return new ApplyFormResponseDto(clubApplyForm);
  }
  
  @Transactional
  public void deleteClubApplyForm(Long clubApplyFormId) {
    List<ClubRecruit> clubRecruits = clubRecruitRepository.findAllByClubApplyFormId(clubApplyFormId);
    boolean hasApplicantToClubApplyForm = clubRecruits.stream()
                                                      .anyMatch(ClubRecruit::hasApplicant);
    if (hasApplicantToClubApplyForm){
      throw new BadRequestException("해당 지원 양식을 사용하는 모집 공고에 지원자가 존재합니다.");
    }
    ClubApplyForm clubApplyForm = ClubApplyFormHelper.findByClubApplyFormId(clubApplyFormRepository, clubApplyFormId);
    clubApplyFormRepository.delete(clubApplyForm);
  }
}
