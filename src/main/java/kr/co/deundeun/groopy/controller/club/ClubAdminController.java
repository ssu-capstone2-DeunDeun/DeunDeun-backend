package kr.co.deundeun.groopy.controller.club;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.controller.club.dto.ClubAdminResponseDto;
import kr.co.deundeun.groopy.controller.club.dto.ClubPositionRequestDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.ClubAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ClubAdminController {

    private final ClubAdminService clubAdminService;

    @PostMapping("/clubs/{clubName}/admins")
    public void giveAdminRole(@PathVariable String clubName, @RequestParam Long userId) {
        clubAdminService.giveAdminRole(clubName, userId);
    }

    @GetMapping("/clubs/{clubName}/members")
    public ResponseEntity<List<ClubAdminResponseDto>> getParticipationInfo(@PathVariable String clubName) {
        return ResponseEntity.ok(clubAdminService.getParticipationInfo(clubName));
    }

    @PostMapping("/clubs/{clubName}/members")
    public ResponseEntity<Void> invite(@PathVariable String clubName, @RequestParam String email) {
        clubAdminService.invite(clubName, email);
        return ResponseEntity.ok().build();
    }

}
