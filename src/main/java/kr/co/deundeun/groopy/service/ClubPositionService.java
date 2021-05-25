package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.controller.club.dto.ClubPositionRequestDto;
import kr.co.deundeun.groopy.controller.club.dto.ClubPositionResponseDto;
import kr.co.deundeun.groopy.dao.ClubPositionRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.dao.ParticipateRepository;
import kr.co.deundeun.groopy.dao.UserRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubPosition;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.AuthorizationException;
import kr.co.deundeun.groopy.helper.ClubHelper;
import kr.co.deundeun.groopy.helper.ClubPositionHelper;
import kr.co.deundeun.groopy.helper.ParticipateHelper;
import kr.co.deundeun.groopy.helper.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ClubPositionService {

    private final UserRepository userRepository;

    private final ClubRepository clubRepository;

    private final ClubPositionRepository clubPositionRepository;

    private final ParticipateRepository participateRepository;

    public List<ClubPositionResponseDto> getClubPositions(String clubName) {
        Club club = ClubHelper.findByClubName(clubRepository, clubName);
        List<ClubPosition> clubPositions = clubPositionRepository.findAllByClub(club);
        if (clubPositions == null)
            return new ArrayList<>();
        return ClubPositionResponseDto.listOf(clubPositions);
    }

    public void addClubPosition(String clubName, ClubPositionRequestDto clubPositionRequestDto) {
        Club club = ClubHelper.findByClubName(clubRepository, clubName);
        ClubPosition clubPosition = clubPositionRequestDto.toClubPosition(club);
        clubPositionRepository.save(clubPosition);
    }

    public void updateClubPosition(Long positionId, ClubPositionRequestDto clubPositionRequestDto) {
        ClubPosition clubPosition = ClubPositionHelper.findClubPositionById(clubPositionRepository, positionId);
        clubPosition.setPositionName(clubPositionRequestDto.getPositionName());
    }

    public void deleteClubPosition(Long positionId) {
        ClubPosition clubPosition = ClubPositionHelper.findClubPositionById(clubPositionRepository, positionId);
        clubPositionRepository.delete(clubPosition);
    }

    public void giveClubPosition(Long participateId, ClubPositionRequestDto clubPositionRequestDto) {
        Participate participate = ParticipateHelper.findParticipateById(participateRepository, participateId);
        Club club = participate.getClubRecruit().getClub();
        ClubPosition clubPosition = clubPositionRepository.findByClubAndPositionName(club, clubPositionRequestDto.getPositionName());
        participate.setClubPosition(clubPosition);
    }
}
