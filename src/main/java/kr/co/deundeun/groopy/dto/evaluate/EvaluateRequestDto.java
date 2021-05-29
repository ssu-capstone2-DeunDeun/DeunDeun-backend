package kr.co.deundeun.groopy.dto.evaluate;

import lombok.Getter;

import java.util.List;

@Getter
public class EvaluateRequestDto {

    private List<Long> clubApplyIds;

    private boolean pass;

}
