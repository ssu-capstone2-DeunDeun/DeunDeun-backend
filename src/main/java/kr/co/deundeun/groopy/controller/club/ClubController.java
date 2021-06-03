package kr.co.deundeun.groopy.controller.club;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.dto.club.ClubRequestDto;
import kr.co.deundeun.groopy.dto.club.ClubResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.dto.club.clubInfo.ClubInfoDto;
import kr.co.deundeun.groopy.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ClubController {

    private final ClubService clubService;

    @PostMapping("/clubs")
    public ResponseEntity<ClubResponseDto> registerClub(@Me User user,
                                                        @RequestBody ClubRequestDto clubRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clubService.registerClub(user, clubRequestDto));
    }

    @GetMapping("/clubs/check")
    public ResponseEntity<Boolean> checkClubName(@RequestParam String clubName) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clubService.isDuplicatedName(clubName));
    }

    @GetMapping("/clubs/{clubId}/approve")
    public ResponseEntity<String> approveClub(@PathVariable Long clubId) {
        clubService.approveClub(clubId);
        return ResponseEntity.ok("동아리 등록이 승인되었습니다");
    }

    @GetMapping("/clubs/{clubName}")
    public ResponseEntity<ClubInfoDto> getClubInfo(@Me User user, @PathVariable String clubName) {
        if (user.getId() == null)
            return ResponseEntity.ok(clubService.getClubInfo(false, clubName));
        else
            return ResponseEntity.ok(clubService.getClubInfo(user, clubName));
    }

    @PatchMapping("/clubs/{clubId}")
    public ResponseEntity<ClubResponseDto> updateClub(@PathVariable Long clubId,
                                                      @RequestBody ClubRequestDto clubRequestDto) {
        return ResponseEntity.ok(clubService.updateClub(clubId, clubRequestDto));
    }

    @DeleteMapping("/clubs/{clubId}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long clubId) {
        clubService.deleteClub(clubId);
        return ResponseEntity.ok().build();
    }

}
