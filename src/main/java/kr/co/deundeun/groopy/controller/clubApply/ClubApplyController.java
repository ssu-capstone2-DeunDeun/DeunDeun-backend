package kr.co.deundeun.groopy.controller.clubApply;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.controller.clubApply.dto.ApplyRequestDto;
import kr.co.deundeun.groopy.controller.clubApply.dto.ApplyResponseDto;
import kr.co.deundeun.groopy.controller.clubApply.dto.ApplySummaryResponseDto;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.QuestionType;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.ClubApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/club/recruit")
@RestController
public class ClubApplyController {

    private final ClubApplyService clubApplyService;

    @PostMapping("/{recruitId}/applies")    // 특정 동아리 모집 공고에 지원서 작성
    public ResponseEntity<ApplyResponseDto> apply(@Me User user, @PathVariable Long recruitId, @RequestBody ApplyRequestDto applyRequestDto) {
        clubApplyService.apply(user, recruitId, applyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{applyId}") // 특정 동아리 모집 공고에 작성했던 지원서 수정
    public ResponseEntity<Void> updateApply(@PathVariable Long applyId,
                                            @RequestBody ApplyRequestDto applyRequestDto) {
        clubApplyService.updateApply(applyId, applyRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping                 // 유저가 그동안 작성 및 지원했던 지원서 리스트 조회
    public ResponseEntity<List<ApplySummaryResponseDto>> getApplies(@Me User user) {
        return ResponseEntity.ok(clubApplyService.getApplies(user));
    }

    @GetMapping("/{applyId}")   // 유저가 작성 및 지원했던 지원서 한 개 조회
    public ResponseEntity<ApplyResponseDto> getApply(@PathVariable Long applyId) {
        return ResponseEntity.ok(clubApplyService.getApply(applyId));
    }

    @DeleteMapping("/{applyId}")  // 유저가 작성 및 지원했던 지원서 삭제
    public ResponseEntity<Void> deleteApply(@PathVariable Long applyId) {
        clubApplyService.deleteApply(applyId);
        return ResponseEntity.ok().build();
    }
}
