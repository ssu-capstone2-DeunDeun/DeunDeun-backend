package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.controller.club.dto.ClubRequestDto;
import kr.co.deundeun.groopy.controller.club.dto.ClubResponseDto;
import kr.co.deundeun.groopy.dao.ClubAdminRepository;
import kr.co.deundeun.groopy.dao.ClubRecruitRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.dao.ParticipateRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubAdmin;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import javax.naming.NameNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClubService {

    private final ClubRepository clubRepository;

    private final ClubRecruitRepository clubRecruitRepository;

    private final ParticipateRepository participateRepository;

    private final ClubAdminRepository clubAdminRepository;


    @Transactional
    public void registerClub(User user, ClubRequestDto clubRequestDto) {
        Club club = clubRequestDto.toClub();
        clubRepository.save(club);

        ClubRecruit clubRecruit = ClubRecruit.builder()
                .club(club)
                .generation(1)
                .build();
        clubRecruitRepository.save(clubRecruit);

        Participate participate = Participate.builder()
                .user(user)
                .clubRecruit(clubRecruit)
                .build();
        participateRepository.save(participate);

        ClubAdmin clubAdmin = ClubAdmin.builder()
                .userId(user.getId())
                .club(club)
                .build();
        clubAdminRepository.save(clubAdmin);

    }

    @Transactional(readOnly = true)
    public ClubResponseDto getClubInfo(String name) throws NameNotFoundException {
        Club club = clubRepository.findByClubName(name).orElseThrow(NameNotFoundException::new);
        return ClubResponseDto.of(club);
    }


}
