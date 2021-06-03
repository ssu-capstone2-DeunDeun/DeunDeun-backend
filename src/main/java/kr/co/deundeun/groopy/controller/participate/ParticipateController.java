package kr.co.deundeun.groopy.controller.participate;

import kr.co.deundeun.groopy.dto.participate.ParticipateRequestDto;
import kr.co.deundeun.groopy.dto.participate.ParticipateResponseDto;
import kr.co.deundeun.groopy.service.ParticipateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/participates")
@RestController
public class ParticipateController {

    private final ParticipateService participateService;

    @PostMapping("/{participateId}/admins")
    public void assignAdminRole(@PathVariable Long participateId) {
        participateService.giveAdminRole(participateId);
    }

    @PatchMapping("/{participateId}/admins")
    public void quitAdminRole(@PathVariable Long participateId) {
        participateService.quitAdminRole(participateId);
    }

    @PostMapping
    public ResponseEntity<ParticipateResponseDto> invite(@RequestBody ParticipateRequestDto participateRequestDto) {
        return ResponseEntity.ok(participateService.invite(participateRequestDto));
    }

    @GetMapping("/club") // 동아리 기준으로 참여자 찾기
    public ResponseEntity<List<ParticipateResponseDto>> getParticipates(@RequestParam String clubName) {
        return ResponseEntity.ok(participateService.getParticipates(clubName));
    }


    @GetMapping("/{participateId}")
    public ResponseEntity<ParticipateResponseDto> getParticipate(@PathVariable Long participateId) {
        return ResponseEntity.ok(participateService.getParticipate(participateId));
    }

    @DeleteMapping("/{participateId}")
    public ResponseEntity<Void> kickOut(@PathVariable Long participateId) {
        participateService.deleteParticipate(participateId);
        return ResponseEntity.ok().build();
    }

}
