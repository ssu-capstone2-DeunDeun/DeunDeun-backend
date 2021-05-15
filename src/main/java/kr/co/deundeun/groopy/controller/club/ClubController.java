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
@RequestMapping("/club")
@RestController
public class ClubController {

    private final ClubService clubService;

    @PostMapping
    public ResponseEntity<ClubResponseDto> registerClub(@Me User user,
                                                        @RequestBody ClubRequestDto clubRequestDto){

        clubService.registerClub(user, clubRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<ClubResponseDto> getClubInfo(@RequestBody String name) throws NameNotFoundException {
        return ResponseEntity.ok(clubService.getClubInfo(name));
    }

}
