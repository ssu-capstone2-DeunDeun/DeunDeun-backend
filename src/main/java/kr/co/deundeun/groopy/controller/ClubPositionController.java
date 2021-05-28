package kr.co.deundeun.groopy.controller;

import kr.co.deundeun.groopy.dao.ParticipateRepository;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.dto.clubPosition.ClubPositionRequestDto;
import kr.co.deundeun.groopy.dto.clubPosition.ClubPositionResponseDto;
import kr.co.deundeun.groopy.dto.clubPosition.participates.PositionChangeDto;
import kr.co.deundeun.groopy.service.ClubPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/positions")
@RestController
public class ClubPositionController {

    private final ClubPositionService clubPositionService;

    @PostMapping
    public ResponseEntity<Void> addClubPosition(@RequestBody ClubPositionRequestDto clubPositionRequestDto) {
        clubPositionService.addClubPosition(clubPositionRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ClubPositionResponseDto>> getClubPositions(@RequestParam String clubName) {
        return ResponseEntity.ok(clubPositionService.getClubPositions(clubName));
    }

    @PatchMapping("/{positionId}")
    public ResponseEntity<Void> updateClubPosition(@PathVariable Long positionId,
                                                   @RequestBody ClubPositionRequestDto clubPositionRequestDto) {
        clubPositionService.updateClubPosition(positionId, clubPositionRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{positionId}")
    public ResponseEntity<Void> deleteClubPosition(@PathVariable Long positionId) {
        clubPositionService.deleteClubPosition(positionId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/participates")
    public ResponseEntity<Void> assignParticipateClubPositions(@RequestBody PositionChangeDto positionChangeDto) {
        clubPositionService.giveClubPosition(positionChangeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/participates")
    public ResponseEntity<Void> deleteParticipateClubPositions(@RequestBody PositionChangeDto positionChangeDto) {
        clubPositionService.deleteParticipateClubPosition(positionChangeDto);
        return ResponseEntity.ok().build();
    }

}
