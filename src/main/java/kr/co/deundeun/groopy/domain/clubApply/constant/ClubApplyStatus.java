package kr.co.deundeun.groopy.domain.clubApply.constant;

import lombok.Getter;

@Getter
public enum ClubApplyStatus {

    WAITING("대기중"),
    FIRST_ROUND_PASS("1차 합격"),
    FIRST_ROUND_FAIL("1차 탈락"),
    FINAL_PASS("최종 합격"),
    FINAL_FAIL("최종 탈락")
    ;

    private final String phase;

    ClubApplyStatus(String phase) {
        this.phase = phase;
    }
}
