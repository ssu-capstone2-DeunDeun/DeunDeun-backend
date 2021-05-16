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
@RequestMapping("/users/{userName}/applies")
@RestController
public class ClubApplyController {

    private final ClubApplyService clubApplyService;

    @PostMapping
    public ResponseEntity<Void> apply(@Me User user, @RequestBody ApplyRequestDto applyRequestDto){
        clubApplyService.apply(user, applyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ApplySummaryResponseDto>> getApplies(@Me User user){
        return ResponseEntity.ok(clubApplyService.getApplies(user));
    }

    @GetMapping("/{applyId}")
    public ResponseEntity<ApplyResponseDto> getApply(@PathVariable Long applyId){
        return ResponseEntity.ok(clubApplyService.getApply(applyId));
    }

    @PatchMapping("/{applyId}")
    public ResponseEntity<Void> updateApply(@PathVariable Long applyId, @RequestBody ApplyRequestDto applyRequestDto){
        clubApplyService.changeApply(applyId, applyRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{applyId}")
    public ResponseEntity<Void> deleteApply(@PathVariable Long applyId){
        clubApplyService.deleteApply(applyId);
        return ResponseEntity.ok().build();
    }
}
