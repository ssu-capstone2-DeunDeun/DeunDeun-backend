package kr.co.deundeun.groopy.helper;

import kr.co.deundeun.groopy.dao.ParticipateRepository;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.exception.IdNotFoundException;

public class ParticipateHelper {

    public static Participate findParticipateById(ParticipateRepository participateRepository, Long participateId){
        return participateRepository.findById(participateId)
                .orElseThrow(() -> new IdNotFoundException("멤버에 해당하는 ID가 없습니다."));
    }
}
