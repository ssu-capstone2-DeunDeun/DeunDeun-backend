package kr.co.deundeun.groopy.controller;

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
    public void giveAdminRole(@PathVariable Long participateId) {
        participateService.giveAdminRole(participateId);
    }

    @PostMapping
    public ResponseEntity<Void> invite(@RequestBody ParticipateRequestDto participateRequestDto) {
        participateService.invite(participateRequestDto);
        return ResponseEntity.ok().build();
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
        participateService.deleteParticipation(participateId);
        return ResponseEntity.ok().build();
    }

}
