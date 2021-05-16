package kr.co.deundeun.groopy.controller.clubRecruit;

import kr.co.deundeun.groopy.controller.clubRecruit.dto.RecruitRequestDto;
import kr.co.deundeun.groopy.service.ClubRecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/recruits")
@RestController
public class ClubRecruitController {

    private final ClubRecruitService clubRecruitService;

    @PostMapping
    public ResponseEntity<Void> addRecruit(@RequestBody RecruitRequestDto recruitRequestDto){

        clubRecruitService.addRecruit(recruitRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
