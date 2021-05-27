package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dto.clubAdmin.ClubAdminResponseDto;
import kr.co.deundeun.groopy.dao.*;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubAdmin;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
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
    private final ClubAdminRepository clubAdminRepository;
    private final ClubRepository clubRepository;
    private final ParticipateRepository participateRepository;
    private final UserRepository userRepository;

    public void giveAdminRole(String clubName, Long userId) {
        Club club = ClubHelper.findByClubName(clubRepository, clubName);
        ClubAdmin clubAdmin = ClubAdmin.builder().club(club).userId(userId).build();
        User user = UserHelper.findUserById(userRepository, userId);

        Participate participate = participateRepository.findByUser(user);
        participate.setAdmin(true);
        clubAdminRepository.save(clubAdmin);
    }

    public List<ClubAdminResponseDto> getParticipationInfo(String clubName) {
        Club club = ClubHelper.findByClubName(clubRepository, clubName);
        List<Participate> participates = participateRepository.findAllByClubRecruit_Club(club);
        return participates.stream().map(ClubAdminResponseDto::of).collect(Collectors.toList());
    }

    public void invite(String clubName, String email) {
        User user = UserHelper.findUserByEmail(userRepository, email);
        Club club = ClubHelper.findByClubName(clubRepository, clubName);

        ClubRecruit clubRecruit = club.getClubRecruits().get(club.getClubRecruits().size() - 1);

        Participate participate = Participate.builder()
                .user(user)
                .clubRecruit(clubRecruit)
                .isAdmin(false)
                .build();
        participateRepository.save(participate);
    }

}
