package kr.co.deundeun.groopy.controller;

import kr.co.deundeun.groopy.dto.clubPosition.ClubPositionRequestDto;
import kr.co.deundeun.groopy.dto.clubPosition.ClubPositionResponseDto;
import kr.co.deundeun.groopy.service.ClubPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ClubPositionController {

    private final ClubPositionService clubPositionService;

    @PostMapping("/clubs/{clubName}/positions")
    public ResponseEntity<Void> addClubPosition(@PathVariable String clubName,
                                                @RequestBody ClubPositionRequestDto clubPositionRequestDto) {
        clubPositionService.addClubPosition(clubName, clubPositionRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/clubs/{clubName}/positions")
    public ResponseEntity<List<ClubPositionResponseDto>> getClubPositions(@PathVariable String clubName){
        return ResponseEntity.ok(clubPositionService.getClubPositions(clubName));
    }

    @PatchMapping("/positions/{positionId}")
    public ResponseEntity<Void> updateClubPosition(@PathVariable Long positionId,
                                                   @RequestBody ClubPositionRequestDto clubPositionRequestDto) {
        clubPositionService.updateClubPosition(positionId, clubPositionRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/positions/{positionId}")
    public ResponseEntity<Void> deleteClubPosition(@PathVariable Long positionId) {
        clubPositionService.deleteClubPosition(positionId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/participates/{participateId}/positions")
    public ResponseEntity<Void> giveClubPosition(@PathVariable Long participateId,
                                                 @RequestBody ClubPositionRequestDto clubPositionRequestDto) {
        clubPositionService.giveClubPosition(participateId, clubPositionRequestDto);
        return ResponseEntity.ok().build();
    }

}
