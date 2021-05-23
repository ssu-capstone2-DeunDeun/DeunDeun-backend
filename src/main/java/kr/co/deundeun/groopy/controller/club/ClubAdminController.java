package kr.co.deundeun.groopy.controller.club;

import kr.co.deundeun.groopy.controller.club.dto.ClubAdminResponseDto;
import kr.co.deundeun.groopy.service.ClubAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/clubs/{clubName}/admins")
@RestController
public class ClubAdminController {

    private final ClubAdminService clubAdminService;

    @PostMapping
    public void giveAdminRole(@PathVariable String clubName, @RequestParam Long userId){
        clubAdminService.giveAdminRole(clubName, userId);
    }

    @GetMapping
    public ResponseEntity<List<ClubAdminResponseDto>> getParticipationInfo(@PathVariable String clubName) {
        return ResponseEntity.ok(clubAdminService.getParticipationInfo(clubName));
    }

}
