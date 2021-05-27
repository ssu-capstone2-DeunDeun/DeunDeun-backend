package kr.co.deundeun.groopy.helper;

import kr.co.deundeun.groopy.dao.ClubApplyRepository;
import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.exception.IdNotFoundException;

public class ClubApplyHelper {

    public static ClubApply findById(ClubApplyRepository clubApplyRepository, Long id) {
        return clubApplyRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("조회하려는 지원서가 존재하지 않습니다."));

    }
}
