package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.controller.clubRecruit.dto.RecruitRequestDto;
import kr.co.deundeun.groopy.controller.clubRecruit.dto.RecruitResponseDto;
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
@Service
public class ClubRecruitService {

    private final ClubRecruitRepository clubRecruitRepository;

    private final ClubRepository clubRepository;

    public void addRecruit(String clubName, RecruitRequestDto recruitRequestDto) {
        Club club = ClubHelper.findByClubName(clubRepository, clubName);
        if (!club.isApproved()) throw new AuthorizationException("동아리 등록 승인이 필요합니다.");
        ClubRecruit clubRecruit = recruitRequestDto.toClubRecruit(club);
        clubRecruitRepository.save(clubRecruit);
    }

    @Transactional(readOnly = true)
    public List<RecruitResponseDto> getRecruits(String clubName) {
        Club club = ClubHelper.findByClubName(clubRepository, clubName);
        return RecruitResponseDto.listOf(clubRecruitRepository
                .findAllByClubAndGenerationGreaterThan(club, 0));
    }

    @Transactional(readOnly = true)
    public RecruitResponseDto getRecruit(Long recruitId) {
        ClubRecruit clubRecruit = ClubRecruitHelper.findById(clubRecruitRepository, recruitId);
        return new RecruitResponseDto(clubRecruit);
    }

    @Transactional
    public void updateRecruit(Long recruitId, RecruitRequestDto recruitRequestDto) {
        ClubRecruit clubRecruit = ClubRecruitHelper.findById(clubRecruitRepository, recruitId);
        clubRecruit.update(recruitRequestDto);
    }

    @Transactional
    public void deleteRecruit(Long recruitId) {
        clubRecruitRepository.deleteById(recruitId);
    }
}
