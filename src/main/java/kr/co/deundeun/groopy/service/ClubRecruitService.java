package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.domain.clubRecruit.constant.ClubRecruitStatus;
import kr.co.deundeun.groopy.dto.clubRecruit.ClubRecruitRequestDto;
import kr.co.deundeun.groopy.dto.clubRecruit.ClubRecruitResponseDto;
import kr.co.deundeun.groopy.dao.ClubRecruitRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.exception.AuthorizationException;
import kr.co.deundeun.groopy.exception.BadRequestException;
import kr.co.deundeun.groopy.helper.ClubHelper;
import kr.co.deundeun.groopy.helper.ClubRecruitHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ClubRecruitService {

    private final ClubRecruitRepository clubRecruitRepository;

    private final ClubRepository clubRepository;

    public void addRecruit(String clubName, ClubRecruitRequestDto clubRecruitRequestDto) {
        Club club = ClubHelper.findByClubName(clubRepository, clubName);
        if (!club.isApproved()) {
            throw new AuthorizationException("동아리 등록 승인이 필요합니다.");
        }
        ClubRecruit clubRecruit = clubRecruitRequestDto.toClubRecruit(club);
        clubRecruitRepository.save(clubRecruit);
    }

    @Transactional(readOnly = true)
    public List<ClubRecruitResponseDto> getRecruits(String clubName) {
        Club club = ClubHelper.findByClubName(clubRepository, clubName);
        return ClubRecruitResponseDto.listOf(club.getClubRecruits());
    }

    public ClubRecruitResponseDto getRecruit(Long recruitId) {
        ClubRecruit clubRecruit = ClubRecruitHelper.findById(clubRecruitRepository, recruitId);
        clubRecruit.increaseViewCount();
        return ClubRecruitResponseDto.of(clubRecruit);
    }

    public void updateRecruit(Long recruitId, ClubRecruitRequestDto clubRecruitRequestDto) {
        ClubRecruit clubRecruit = ClubRecruitHelper.findById(clubRecruitRepository, recruitId);
        clubRecruit.update(clubRecruitRequestDto);
    }

    public void deleteRecruit(Long recruitId) {
        ClubRecruit clubRecruit = ClubRecruitHelper.findById(clubRecruitRepository, recruitId);
        if (clubRecruit.hasApplicant()) {
            throw new BadRequestException("해당 지원 양식을 사용하는 모집 공고에 지원자가 존재합니다.");
        }
        clubRecruit.deleteClubApplyForm();
        clubRecruit.deleteClub();
        clubRecruitRepository.deleteById(clubRecruit.getId());
    }

    public void scheduling() {
        List<ClubRecruit> clubRecruits =
                clubRecruitRepository.findAllByClubRecruitStatusIsNot(ClubRecruitStatus.END);
        clubRecruits.forEach(ClubRecruit::updateClubRecruitStatus);
    }
}
