package kr.co.deundeun.groopy.controller.club;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.config.security.UserPrincipal;
import kr.co.deundeun.groopy.controller.club.dto.ClubRequestDto;
import kr.co.deundeun.groopy.controller.club.dto.ClubResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import java.util.stream.Collectors;

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
    public ResponseEntity<ClubResponseDto> getClubInfo(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable String clubName){
        if(userPrincipal == null)
            return ResponseEntity.ok(clubService.getClubInfo(false, clubName));
        else
            return ResponseEntity.ok(clubService.getClubInfo(userPrincipal.getUser(), clubName));
    }

    @PatchMapping("/{clubName}")
    public ResponseEntity<Void> updateClub(@PathVariable String clubName, @RequestBody ClubRequestDto clubRequestDto){
        clubService.updateClub(clubName, clubRequestDto);
        return ResponseEntity.ok().build();
    }

}
