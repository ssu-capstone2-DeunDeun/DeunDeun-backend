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
@RequestMapping("/clubs/{clubName}/recruits")
@RestController
public class ClubRecruitController {

    private final ClubRecruitService clubRecruitService;

    @PostMapping
    public ResponseEntity<Void> addRecruit(@PathVariable String clubName, @RequestBody RecruitRequestDto recruitRequestDto){
        clubRecruitService.addRecruit(clubName, recruitRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<RecruitResponseDto>> getRecruits(@PathVariable String clubName){
        return ResponseEntity.ok(clubRecruitService.getRecruits(clubName));
    }

    @GetMapping("/{recruitId}")
    public ResponseEntity<RecruitResponseDto> getRecruit(@PathVariable Long recruitId){
        return ResponseEntity.ok(clubRecruitService.getRecruit(recruitId));
    }

    @PatchMapping("/{recruitId}")
    public ResponseEntity<Void> updateRecruit(@PathVariable Long recruitId, @RequestBody RecruitRequestDto recruitRequestDto){
        clubRecruitService.updateRecruit(recruitId, recruitRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{recruitId}")
    public ResponseEntity<Void> deleteRecruit(@PathVariable Long recruitId){
        clubRecruitService.deleteRecruit(recruitId);
        return ResponseEntity.ok().build();
    }


}
