package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.controller.club.dto.ClubAdminResponseDto;
import kr.co.deundeun.groopy.dao.ClubAdminRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.dao.ParticipateRepository;
import kr.co.deundeun.groopy.dao.UserRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubAdmin;
import kr.co.deundeun.groopy.domain.hashtag.UserHashtag;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.ClubNotFoundException;
import kr.co.deundeun.groopy.helper.ClubHelper;
import kr.co.deundeun.groopy.helper.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        clubAdminRepository.save(clubAdmin);
    }

    public List<ClubAdminResponseDto> getParticipationInfo(String clubName) {
        Club club = ClubHelper.findByClubName(clubRepository, clubName);
        List<Participate> participates = participateRepository.findAllByClubRecruit_Club(club);

        return participates.stream().map(ClubAdminResponseDto::of).collect(Collectors.toList());
    }

    public void invite(String clubName, String nickname) {
        User user = UserHelper.findUserByNickname(userRepository, nickname);
        Club club = ClubHelper.findByClubName(clubRepository, clubName);


    }
}
