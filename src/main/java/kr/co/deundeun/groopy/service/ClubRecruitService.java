package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.controller.clubRecruit.dto.RecruitSummaryResponseDto;
import kr.co.deundeun.groopy.controller.clubRecruit.dto.RecruitRequestDto;
import kr.co.deundeun.groopy.controller.clubRecruit.dto.RecruitResponseDto;
import kr.co.deundeun.groopy.dao.ClubRecruitRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.exception.ClubNotFoundException;
import kr.co.deundeun.groopy.exception.ClubRecruitNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClubRecruitService {

    private final ClubRecruitRepository clubRecruitRepository;

    private final ClubRepository clubRepository;

    public void addRecruit(String clubName, RecruitRequestDto recruitRequestDto){
        Club club = clubRepository.findByClubName(clubName).orElseThrow(ClubNotFoundException::new);
        clubRecruitRepository.save(recruitRequestDto.toClubRecruit(club));
    }

    public List<RecruitSummaryResponseDto> getRecruits(String clubName){
        Club club = clubRepository.findByClubName(clubName).orElseThrow(ClubNotFoundException::new);
        return RecruitSummaryResponseDto.listOf(clubRecruitRepository.findAllByClub(club));
    }

    public RecruitResponseDto getRecruit(Long recruitId){
        ClubRecruit clubRecruit = clubRecruitRepository.findById(recruitId).orElseThrow(ClubRecruitNotFoundException::new);
        return new RecruitResponseDto(clubRecruit);
    }

    public void updateRecruit(Long recruitId, RecruitRequestDto recruitRequestDto){
        ClubRecruit clubRecruit = clubRecruitRepository.findById(recruitId).orElseThrow(ClubRecruitNotFoundException::new);
        clubRecruit.update(recruitRequestDto);
        clubRecruitRepository.save(clubRecruit);
    }

    public void deleteRecruit(Long recruitId){
        clubRecruitRepository.deleteById(recruitId);
    }
}
