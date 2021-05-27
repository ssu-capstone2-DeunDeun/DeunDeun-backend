package kr.co.deundeun.groopy.helper;

import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.exception.ClubNotFoundException;

public class ClubHelper {

    public static Club findByClubName(ClubRepository clubRepository, String clubName){
        return clubRepository.findByClubName(clubName).orElseThrow(ClubNotFoundException::new);
    }

    public static Club findClubById(ClubRepository clubRepository, Long clubId){
        return clubRepository.findById(clubId).orElseThrow(ClubNotFoundException::new);
    }
}
