package kr.co.deundeun.groopy.controller.clubRecruit;

import kr.co.deundeun.groopy.controller.clubRecruit.dto.RecruitRequestDto;
import kr.co.deundeun.groopy.controller.clubRecruit.dto.RecruitResponseDto;
import kr.co.deundeun.groopy.service.ClubRecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ClubRecruitController {

    private final ClubRecruitService clubRecruitService;

    @PostMapping("clubs/{clubName}/recruits") // 모집공고 생성
    public ResponseEntity<Void> addRecruit(@PathVariable String clubName, @RequestBody RecruitRequestDto recruitRequestDto){
        clubRecruitService.addRecruit(clubName, recruitRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("clubs/{clubName}/recruits") // 동아리 모집공고 전부 불러오기
    public ResponseEntity<List<RecruitResponseDto>> getRecruits(@PathVariable String clubName){
        return ResponseEntity.ok(clubRecruitService.getRecruits(clubName));
    }

    @GetMapping("recruits/{recruitId}") // 동아리 특정 모집공고 가져오기
    public ResponseEntity<RecruitResponseDto> getRecruit(@PathVariable Long recruitId){
        return ResponseEntity.ok(clubRecruitService.getRecruit(recruitId));
    }

    @PatchMapping("recruits/{recruitId}")  // 동아리 모집공고 수정하기
    public ResponseEntity<Void> updateRecruit(@PathVariable Long recruitId, @RequestBody RecruitRequestDto recruitRequestDto){
        clubRecruitService.updateRecruit(recruitId, recruitRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("recruits/{recruitId}") // 동아리 모집공고 삭제하기
    public ResponseEntity<Void> deleteRecruit(@PathVariable Long recruitId){
        clubRecruitService.deleteRecruit(recruitId);
        return ResponseEntity.ok().build();
    }
}
