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

import javax.naming.NameNotFoundException;

@RequiredArgsConstructor
@RequestMapping("/clubs")
@RestController
public class ClubController {

    private final ClubService clubService;

    @PostMapping
    public ResponseEntity<ClubResponseDto> registerClub(@Me User user, @RequestBody ClubRequestDto clubRequestDto){
        clubService.registerClub(user, clubRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{clubName}")
    public ResponseEntity<ClubResponseDto> getClubInfo(@PathVariable String clubName) throws NameNotFoundException {
        return ResponseEntity.ok(clubService.getClubInfo(clubName));
    }

    @PatchMapping("/{clubName}")
    public ResponseEntity<Void> updateClub(@PathVariable String clubName, @RequestBody ClubRequestDto clubRequestDto) throws NameNotFoundException {
        clubService.updateClub(clubName, clubRequestDto);
        return ResponseEntity.ok().build();
    }

}
