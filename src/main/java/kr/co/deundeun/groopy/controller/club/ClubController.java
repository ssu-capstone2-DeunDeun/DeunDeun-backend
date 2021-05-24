package kr.co.deundeun.groopy.controller.club;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.controller.club.dto.ClubRequestDto;
import kr.co.deundeun.groopy.controller.club.dto.ClubResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ClubController {

    private final ClubService clubService;

    @PostMapping("/clubs")
    public ResponseEntity<ClubResponseDto> registerClub(@Me User user, @RequestBody ClubRequestDto clubRequestDto) {
        clubService.registerClub(user, clubRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/clubs/{clubName}/approve")
    public ResponseEntity<String> approveClub(@PathVariable String clubName) {
        clubService.approveClub(clubName);
        return ResponseEntity.ok("동아리 등록이 승인되었습니다");
    }

    @GetMapping("/clubs/{clubName}")
    public ResponseEntity<ClubResponseDto> getClubInfo(@Me User user, @PathVariable String clubName) {
        if (user.getId() == null)
            return ResponseEntity.ok(clubService.getClubInfo(false, clubName));
        else
            return ResponseEntity.ok(clubService.getClubInfo(user, clubName));
    }

    @PatchMapping("/clubs/{clubName}")
    public ResponseEntity<Void> updateClub(@PathVariable String clubName, @RequestBody ClubRequestDto clubRequestDto) {
        clubService.updateClub(clubName, clubRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/liked/clubs")
    public ResponseEntity<List<ClubResponseDto>> getLikedClubs(@Me User user) {
        return ResponseEntity.ok(clubService.getLikedClubs(user));
    }

}
