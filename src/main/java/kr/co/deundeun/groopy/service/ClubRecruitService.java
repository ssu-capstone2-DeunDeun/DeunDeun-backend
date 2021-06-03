package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dto.clubRecruit.ClubRecruitRequestDto;
import kr.co.deundeun.groopy.dto.clubRecruit.ClubRecruitResponseDto;
import kr.co.deundeun.groopy.dao.ClubRecruitRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.exception.AuthorizationException;
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
        if (!club.isApproved()) throw new AuthorizationException("동아리 등록 승인이 필요합니다.");
        ClubRecruit clubRecruit = clubRecruitRequestDto.toClubRecruit(club);
        clubRecruitRepository.save(clubRecruit);
    }

    @Transactional(readOnly = true)
    public List<ClubRecruitResponseDto> getRecruits(String clubName) {
        Club club = ClubHelper.findByClubName(clubRepository, clubName);
        return ClubRecruitResponseDto.listOf(clubRecruitRepository.findAllByClub(club));
    }

    @Transactional(readOnly = true)
    public ClubRecruitResponseDto getRecruit(Long recruitId) {
        ClubRecruit clubRecruit = ClubRecruitHelper.findById(clubRecruitRepository, recruitId);
        return new ClubRecruitResponseDto(clubRecruit);
    }

    public void updateRecruit(Long recruitId, ClubRecruitRequestDto clubRecruitRequestDto) {
        ClubRecruit clubRecruit = ClubRecruitHelper.findById(clubRecruitRepository, recruitId);
        clubRecruit.update(clubRecruitRequestDto);
    }

    public void deleteRecruit(Long recruitId) {
        clubRecruitRepository.deleteById(recruitId);
    }
}
