package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dto.clubPosition.ClubPositionRequestDto;
import kr.co.deundeun.groopy.dto.clubPosition.ClubPositionResponseDto;
import kr.co.deundeun.groopy.dao.ClubPositionRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.dao.ParticipateRepository;
import kr.co.deundeun.groopy.dao.UserRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubPosition;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.dto.clubPosition.participates.PositionChangeDto;
import kr.co.deundeun.groopy.helper.ClubHelper;
import kr.co.deundeun.groopy.helper.ClubPositionHelper;
import kr.co.deundeun.groopy.helper.ParticipateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ClubPositionService {

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

    public void addClubPosition(ClubPositionRequestDto clubPositionRequestDto) {
        Club club = ClubHelper.findClubById(clubRepository, clubPositionRequestDto.getClubId());
        ClubPosition clubPosition = clubPositionRequestDto.toClubPosition(club);
        clubPositionRepository.save(clubPosition);
    }

    public void updateClubPosition(Long positionId, ClubPositionRequestDto clubPositionRequestDto) {
        ClubPosition clubPosition = ClubPositionHelper.findClubPositionById(clubPositionRepository, positionId);
        clubPosition.setPositionName(clubPositionRequestDto.getPositionName());
    }

    public void deleteClubPosition(Long positionId) {
        ClubPosition clubPosition = ClubPositionHelper.findClubPositionById(clubPositionRepository, positionId);
        List<Participate> participates = participateRepository.findAllByClubPosition(clubPosition);
        participates.forEach(participate -> participate.setClubPosition(null));
        clubPositionRepository.delete(clubPosition);
    }

    public void giveClubPosition(PositionChangeDto positionChangeDto) {
        List<Participate> participates =
                ParticipateHelper.findAllParticipateById(participateRepository, positionChangeDto.getParticipateIds());

        ClubPosition clubPosition = ClubPositionHelper.findClubPositionById(clubPositionRepository, positionChangeDto.getPositionId());
        participates.forEach(participate -> participate.setClubPosition(clubPosition));
    }

    public void deleteParticipateClubPosition(PositionChangeDto positionChangeDto) {
        List<Participate> participates =
                ParticipateHelper.findAllParticipateById(participateRepository, positionChangeDto.getParticipateIds());
        participates.forEach(participate -> participate.setClubPosition(null));
    }
}
