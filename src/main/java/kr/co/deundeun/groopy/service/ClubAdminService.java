package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dto.clubAdmin.ClubAdminResponseDto;
import kr.co.deundeun.groopy.dao.*;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.helper.ClubHelper;
import kr.co.deundeun.groopy.helper.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ClubAdminService {
    private final ClubRepository clubRepository;
    private final ParticipateRepository participateRepository;
    private final UserRepository userRepository;

    public void giveAdminRole(String clubName, Long userId) {
        User user = UserHelper.findUserById(userRepository, userId);

        Participate participate = participateRepository.findByUser(user);
        participate.setAdmin(true);
    }

    public List<ClubAdminResponseDto> getParticipationInfo(String clubName) {
        Club club = ClubHelper.findByClubName(clubRepository, clubName);
        List<Participate> participates = participateRepository.findAllByClub(club);
        return participates.stream().map(ClubAdminResponseDto::of).collect(Collectors.toList());
    }

    public void invite(String clubName, String email) {
        User user = UserHelper.findUserByEmail(userRepository, email);
        Club club = ClubHelper.findByClubName(clubRepository, clubName);

        Participate participate = new Participate(user, club, false);
        participateRepository.save(participate);
    }

}
