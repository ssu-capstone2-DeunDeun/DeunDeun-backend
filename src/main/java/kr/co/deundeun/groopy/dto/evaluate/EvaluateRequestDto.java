package kr.co.deundeun.groopy.dto.evaluate;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class EvaluateRequestDto {

    private List<Long> clubApplyIds;

    private boolean pass;

}
