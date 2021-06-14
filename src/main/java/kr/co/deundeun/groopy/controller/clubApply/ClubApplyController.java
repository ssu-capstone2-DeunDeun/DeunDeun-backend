package kr.co.deundeun.groopy.controller.clubApply;

import io.swagger.annotations.ApiOperation;
import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.dto.clubApply.ApplyInfoDto;
import kr.co.deundeun.groopy.dto.clubApply.ApplyRequestDto;
import kr.co.deundeun.groopy.dto.clubApply.ApplyResponseDto;
import kr.co.deundeun.groopy.dto.clubApply.ApplySummaryResponseDto;
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

    @ApiOperation(
        value = "특정 동아리 모집 공고에 지원서 작성",
        notes = "<h3>\n"
            + "- 모집공고 id를 받아서 특정 동아리에 유저가 지원서를 작성합니다.\n"
            + "\n"
            + "</h3>"
    )
    @PostMapping("/{recruitId}/applies")    // 특정 동아리 모집 공고에 지원서 작성
    public ResponseEntity<ApplyResponseDto> apply(@Me User user, @PathVariable Long recruitId, @RequestBody ApplyRequestDto applyRequestDto) {
        clubApplyService.apply(user, recruitId, applyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(
        value = "동아리에 지원한 특정 공고의 지원자 목록 불러오기, 지원자 관리의 첫화면 입니다.",
        notes = "<h3>\n"
            + "- 모집 공고 id를 받아서, 동아리에 지원한 특정 공고의 지원자 목록을 불러옵니다.\n"
            + "- 지원서는 공고에 묶이기 때문에 모집 공고 id를 받습니다. \n"
            + "\n"
            + "</h3>"
    )
    @GetMapping("/{recruitId}/applies")     // 동아리에 지원한 특정 공고의 지원자 목록 불러오기, [지원자 관리 첫화면]
    public ResponseEntity<List<ApplyInfoDto>> getAppliesInfo(@Me User user, @PathVariable Long recruitId){
        return ResponseEntity.ok(clubApplyService.getAppliesInfo(user, recruitId));
    }

    @ApiOperation(
        value = "유저가 그동안 작성 후, 지원했던 지원서 리스트를 조회합니다.",
        notes = "<h3>\n"
            + "- 마이페이지 - 나의 지원 목록에서 사용됩니다. \n"
            + "\n"
            + "</h3>"
    )
    @GetMapping("/applies")              // 유저가 그동안 작성 및 지원했던 지원서 리스트 조회
    public ResponseEntity<List<ApplySummaryResponseDto>> getApplies(@Me User user) {
        return ResponseEntity.ok(clubApplyService.getApplies(user));
    }

    @ApiOperation(
        value = "유저가 작성, 지원 했던 지원서 한 개를 조회",
        notes = "<h3>\n"
            + "- 지원서 한 개를 조회합니다. \n"
            + "- 유저가 본인의 지원서를 조회하거나, 관리자가 지원자의 지원서를 조회합니다. \n"
            + "\n"
            + "</h3>"
    )
    @GetMapping("/applies/{applyId}")   // 유저가 작성 및 지원했던 지원서 한 개 조회 OR 관리자가 조회
    public ResponseEntity<ApplyResponseDto> getApply(@PathVariable Long applyId) {
        return ResponseEntity.ok(clubApplyService.getApply(applyId));
    }

    @ApiOperation(
        value = "applyId를 갖는 지원서의 내용을 수정합니다.",
        notes = "<h3>\n"
            + "- applyId를 갖는 지원서의 내용을 수정합니다. \n"
            + "\n"
            + "</h3>"
    )
    @PatchMapping("/applies/{applyId}") // 특정 동아리 모집 공고에 작성했던 지원서 수정
    public ResponseEntity<Void> updateApply(@PathVariable Long applyId,
        @RequestBody ApplyRequestDto applyRequestDto) {
        clubApplyService.updateApply(applyId, applyRequestDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(
        value = "applyId를 갖는 지원서의 삭제합니다.",
        notes = "<h3>\n"
            + "- applyId를 갖는 지원서의 내용을 삭제합니다. \n"
            + "\n"
            + "</h3>"
    )
    @DeleteMapping("/{applyId}")  // 유저가 작성 및 지원했던 지원서 삭제
    public ResponseEntity<Void> deleteApply(@PathVariable Long applyId) {
        clubApplyService.deleteApply(applyId);
        return ResponseEntity.ok().build();
    }
}
