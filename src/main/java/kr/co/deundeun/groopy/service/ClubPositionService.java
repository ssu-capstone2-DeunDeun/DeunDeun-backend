package kr.co.deundeun.groopy.service;

import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.dto.clubPosition.ClubPositionRequestDto;
import kr.co.deundeun.groopy.dto.clubPosition.ClubPositionResponseDto;
import kr.co.deundeun.groopy.dao.ClubPositionRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.dao.ParticipateRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubPosition;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.dto.clubPosition.participates.PositionChangeRequestDto;
import kr.co.deundeun.groopy.dto.clubPosition.participates.PositionChangeResponseDto;
import kr.co.deundeun.groopy.helper.ClubHelper;
import kr.co.deundeun.groopy.helper.ClubPositionHelper;
import kr.co.deundeun.groopy.helper.ParticipateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    if (clubPositions == null) {
      return new ArrayList<>();
    }
    return ClubPositionResponseDto.listOf(clubPositions);
  }

  public ClubPositionResponseDto addClubPosition(ClubPositionRequestDto clubPositionRequestDto) {
    Club club = ClubHelper.findClubById(clubRepository, clubPositionRequestDto.getClubId());
    ClubPosition clubPosition = clubPositionRequestDto.toClubPosition(club);
    clubPositionRepository.save(clubPosition);
    return new ClubPositionResponseDto(clubPosition);
  }

  public ClubPositionResponseDto updateClubPosition(Long positionId,
                                                    ClubPositionRequestDto clubPositionRequestDto) {
    ClubPosition clubPosition = ClubPositionHelper.findClubPositionById(clubPositionRepository,
                                                                        positionId);
    clubPosition.setPositionName(clubPositionRequestDto.getPositionName());
    return new ClubPositionResponseDto(clubPosition);
  }

  public ClubPositionResponseDto deleteClubPosition(Long positionId) {
    ClubPosition clubPosition = ClubPositionHelper.findClubPositionById(clubPositionRepository,
                                                                        positionId);
    List<Participate> participates = participateRepository.findAllByClubPosition(clubPosition);
    participates.forEach(participate -> participate.setClubPosition(null));
    clubPositionRepository.delete(clubPosition);
    return new ClubPositionResponseDto(clubPosition);
  }

  public PositionChangeResponseDto giveClubPosition(
      PositionChangeRequestDto positionChangeRequestDto) {
    ClubPosition clubPosition = ClubPositionHelper.findClubPositionById(clubPositionRepository,
                                                                        positionChangeRequestDto.getPositionId());
    Club club = clubPosition.getClub();
    List<Participate> participates = ParticipateHelper.findAllParticipateByIdAndClub(
        participateRepository,
        positionChangeRequestDto.getParticipateIds(), club);

    participates.forEach(participate -> participate.setClubPosition(clubPosition));

    List<Long> participateIds = participates.stream()
                                            .map(BaseEntity::getId)
                                            .collect(Collectors.toList());
    return new PositionChangeResponseDto(participateIds, clubPosition);
  }

  public PositionChangeResponseDto deleteParticipateClubPosition(
      PositionChangeRequestDto positionChangeRequestDto) {
    ClubPosition clubPosition = ClubPositionHelper.findClubPositionById(clubPositionRepository,
                                                                        positionChangeRequestDto.getPositionId());
    Club club = clubPosition.getClub();
    List<Participate> participates = ParticipateHelper.findAllParticipateByIdAndClub(
        participateRepository,
        positionChangeRequestDto.getParticipateIds(), club);
    participates.forEach(participate -> participate.setClubPosition(null));
    List<Long> participateIds = participates.stream()
                                            .map(BaseEntity::getId)
                                            .collect(Collectors.toList());
    return new PositionChangeResponseDto(participateIds, clubPosition);
  }
}
