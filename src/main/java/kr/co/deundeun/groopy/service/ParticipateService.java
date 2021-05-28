package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dto.participate.ParticipateRequestDto;
import kr.co.deundeun.groopy.dto.participate.ParticipateResponseDto;
import kr.co.deundeun.groopy.dao.*;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.DuplicateResourceException;
import kr.co.deundeun.groopy.helper.ClubHelper;
import kr.co.deundeun.groopy.helper.ParticipateHelper;
import kr.co.deundeun.groopy.helper.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ParticipateService {
    private final ClubRepository clubRepository;
    private final ParticipateRepository participateRepository;
    private final UserRepository userRepository;

    public void giveAdminRole(Long participateId) {
        Participate participate = ParticipateHelper.findParticipateById(participateRepository, participateId);
        participate.setAdmin(true);
    }

    @Transactional(readOnly = true)
    public List<ParticipateResponseDto> getParticipates(String clubName) {
        Club club = ClubHelper.findByClubName(clubRepository, clubName);
        List<Participate> participates = participateRepository.findAllByClub(club);
        return participates.stream().map(ParticipateResponseDto::of).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ParticipateResponseDto getParticipate(Long participateId) {
        Participate participate = ParticipateHelper.findParticipateById(participateRepository, participateId);
        return ParticipateResponseDto.of(participate);
    }

    public void invite(ParticipateRequestDto participateRequestDto) {
        User user = UserHelper.findUserByEmail(userRepository, participateRequestDto.getEmail());
        Club club = ClubHelper.findByClubName(clubRepository, participateRequestDto.getClubName());

        if (participateRepository.existsByUserAndClub(user, club))
            throw new DuplicateResourceException("이미 등록된 회원입니다.");
        Participate participate = new Participate(user, club, false);
        participateRepository.save(participate);
    }

    public void deleteParticipate(Long participateId) {
        participateRepository.deleteById(participateId);
    }

    public void quitAdminRole(Long participateId) {
        Participate participate = ParticipateHelper.findParticipateById(participateRepository, participateId);
        participate.setAdmin(false);
    }
}
