package kr.co.deundeun.groopy.helper;

import kr.co.deundeun.groopy.dao.ClubApplyFormRepository;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubApplyForm;
import kr.co.deundeun.groopy.exception.IdNotFoundException;

public class ClubApplyFormHelper {

  public static ClubApplyForm findByClubApplyFormId(ClubApplyFormRepository clubApplyFormRepository,
      Long id) {
    return clubApplyFormRepository.findById(id)
                                  .orElseThrow(
                                      () -> new IdNotFoundException("존재하지 않는 지원서 양식 ID 입니다."));

  }
}
