package kr.co.deundeun.groopy.helper;

import kr.co.deundeun.groopy.dao.ClubPositionRepository;
import kr.co.deundeun.groopy.domain.club.ClubPosition;
import kr.co.deundeun.groopy.exception.IdNotFoundException;

public class ClubPositionHelper {

    public static ClubPosition findClubPositionById(ClubPositionRepository clubPositionRepository, Long clubPositionId){
        return clubPositionRepository.findById(clubPositionId)
                .orElseThrow(() -> new IdNotFoundException("역할에 해당하는 ID가 없습니다."));
    }
}
