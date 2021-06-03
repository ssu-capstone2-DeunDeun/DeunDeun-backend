package kr.co.deundeun.groopy.controller.evaluation;

import kr.co.deundeun.groopy.dto.common.page.PageRequestDto;
import kr.co.deundeun.groopy.dto.evaluate.ClubApplyStatusDto;
import kr.co.deundeun.groopy.dto.evaluate.EvaluateRequestDto;
import kr.co.deundeun.groopy.dto.evaluate.EvaluateResponseDto;
import kr.co.deundeun.groopy.service.EvaluateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EvaluationController {

    private final EvaluateService evaluateService;

    @PostMapping("/evaluations/first")
    public ResponseEntity<Void> evaluateFirstClubApply(@RequestBody EvaluateRequestDto evaluateRequestDto) {
        evaluateService.evaluateFirstClubApply(evaluateRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/evaluations/final")
    public ResponseEntity<Void> evaluateFinalClubApply(@RequestBody EvaluateRequestDto evaluateRequestDto) {
        evaluateService.evaluateFinalClubApply(evaluateRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/evaluations")
    public ResponseEntity<Page<EvaluateResponseDto>> getEvaluations(@RequestParam String clubName,
                                                                    final PageRequestDto pageRequestDto) {
        return ResponseEntity.ok(evaluateService.getEvaluations(clubName, pageRequestDto));
    }

    @GetMapping("/evaluations/{clubApplyId}")
    public ResponseEntity<EvaluateResponseDto> getEvaluation(@PathVariable Long clubApplyId) {
        return ResponseEntity.ok(evaluateService.getEvaluation(clubApplyId));
    }

}
