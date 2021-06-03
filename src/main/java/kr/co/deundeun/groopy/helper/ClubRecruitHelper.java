package kr.co.deundeun.groopy.helper;

import kr.co.deundeun.groopy.dao.ClubRecruitRepository;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.exception.IdNotFoundException;

public class ClubRecruitHelper {

    public static ClubRecruit findById(ClubRecruitRepository clubRecruitRepository, Long id){
        return clubRecruitRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("존재하지 않는 모집 공고입니다."));
    }
}
