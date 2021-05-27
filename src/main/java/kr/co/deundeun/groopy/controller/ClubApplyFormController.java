package kr.co.deundeun.groopy.controller;

import java.util.List;
import kr.co.deundeun.groopy.dto.clubApplyForm.ApplyFormRequestDto;
import kr.co.deundeun.groopy.dto.clubApplyForm.ApplyFormResponseDto;
import kr.co.deundeun.groopy.service.ClubApplyFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/clubs/{clubName}/forms")
@RequiredArgsConstructor
@RestController
public class ClubApplyFormController {

  private final ClubApplyFormService clubApplyFormService;

  @PostMapping   // 지원서 양식 작성
  public ResponseEntity<Void> addClubApplyForm(@PathVariable String clubName, @RequestBody ApplyFormRequestDto applyFormRequestDto){
    clubApplyFormService.addClubApplyForm(clubName, applyFormRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping  // 특정 동아리의 지원서 양식 리스트 조회
  public ResponseEntity<List<ApplyFormResponseDto>> getApplyForms(@PathVariable String clubName){
    return ResponseEntity.ok(clubApplyFormService.getApplyForms(clubName));
  }

  @GetMapping("/{clubApplyFormId}")   // 특정 동아리의 지원서 양식 한 개 조회
  public ResponseEntity<ApplyFormResponseDto> getApplyForm(@PathVariable Long clubApplyFormId){
    return ResponseEntity.ok(clubApplyFormService.getApplyForm(clubApplyFormId));
  }

  /*
  - 수정불가
  - 지원자가 있다면, 삭제도 불가
   */

  @DeleteMapping("/{clubApplyFormId}")
  public ResponseEntity<Void> deleteClubApplyForm(@PathVariable Long clubApplyFormId){
    clubApplyFormService.deleteClubApplyForm(clubApplyFormId);
    return ResponseEntity.ok().build();
  }

}
