package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dao.ClubAdminRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubAdmin;
import kr.co.deundeun.groopy.exception.ClubNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClubAdminService {
    private final ClubAdminRepository clubAdminRepository;
    private final ClubRepository clubRepository;

    public void giveAdminRole(String clubName, Long userId) {
        Club club = clubRepository.findByClubName(clubName).orElseThrow(ClubNotFoundException::new);
        ClubAdmin clubAdmin = ClubAdmin.builder().club(club).userId(userId).build();
        clubAdminRepository.save(clubAdmin);
    }
}
