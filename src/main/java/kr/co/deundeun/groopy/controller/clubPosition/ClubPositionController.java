package kr.co.deundeun.groopy.controller.clubPosition;

import io.swagger.annotations.ApiOperation;
import kr.co.deundeun.groopy.dto.clubPosition.ClubPositionRequestDto;
import kr.co.deundeun.groopy.dto.clubPosition.ClubPositionResponseDto;
import kr.co.deundeun.groopy.dto.clubPosition.participates.PositionChangeRequestDto;
import kr.co.deundeun.groopy.dto.clubPosition.participates.PositionChangeResponseDto;
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

  @ApiOperation(
      value = "동아리 역할 종류 추가",
      notes = "<h3>\n"
          + "- 동아리 id와 역할 이름을 받아서 해당 동아리에 동아리 역할 종류를 추가합니다.\n"
          + "\n"
          + "</h3>"
  )
  @PostMapping
  public ResponseEntity<ClubPositionResponseDto> addClubPosition(
      @RequestBody ClubPositionRequestDto clubPositionRequestDto) {
    return ResponseEntity.ok(clubPositionService.addClubPosition(clubPositionRequestDto));
  }

  @ApiOperation(
      value = "동아리 역할 종류들 조회",
      notes = "<h3>\n"
          + "- 동아리 이름을 받아서 해당 동아리의 동아리 역할들을 조회합니다.\n"
          + "\n"
          + "</h3>"
  )
  @GetMapping
  public ResponseEntity<List<ClubPositionResponseDto>> getClubPositions(
      @RequestParam String clubName) {
    return ResponseEntity.ok(clubPositionService.getClubPositions(clubName));
  }

  @ApiOperation(
      value = "동아리 역할 종류 수정",
      notes = "<h3>\n"
          + "- 동아리 id와 역할 이름을 받아서 해당 동아리의 동아리 역할 이름을 수정합니다.\n"
          + "\n"
          + "</h3>"
  )
  @PatchMapping("/{positionId}")
  public ResponseEntity<ClubPositionResponseDto> updateClubPosition(@PathVariable Long positionId,
                                                                    @RequestBody ClubPositionRequestDto clubPositionRequestDto) {
    return ResponseEntity.ok(
        clubPositionService.updateClubPosition(positionId, clubPositionRequestDto));
  }

  @ApiOperation(
      value = "동아리 역할 종류 삭제",
      notes = "<h3>\n"
          + "- 동아리 id와 역할 이름을 받아서 해당 동아리의 동아리 역할 이름을 삭제합니다.\n"
          + "\n"
          + "</h3>"
  )
  @DeleteMapping("/{positionId}")
  public ResponseEntity<ClubPositionResponseDto> deleteClubPosition(@PathVariable Long positionId) {
    return ResponseEntity.ok(clubPositionService.deleteClubPosition(positionId));
  }

  @ApiOperation(
      value = "동아리 멤버들 역할 변경",
      notes = "<h3>\n"
          + "- 동아리 멤버들과 역할 id를 받아서 멤버들의 역할을 변경합니다.\n"
          + "\n"
          + "</h3>"
  )
  @PatchMapping("/participates")
  public ResponseEntity<PositionChangeResponseDto> assignParticipateClubPositions(
      @RequestBody PositionChangeRequestDto positionChangeRequestDto) {
    return ResponseEntity.ok(clubPositionService.giveClubPosition(positionChangeRequestDto));
  }

  @ApiOperation(
      value = "동아리 멤버들 역할 삭제",
      notes = "<h3>\n"
          + "- 동아리 멤버들과 역할 id를 받아서 멤버들의 역할을 삭제합니다.\n"
          + "\n"
          + "</h3>"
  )
  @DeleteMapping("/participates")
  public ResponseEntity<PositionChangeResponseDto> deleteParticipateClubPositions(
      @RequestBody PositionChangeRequestDto positionChangeRequestDto) {
    return ResponseEntity.ok(clubPositionService.deleteParticipateClubPosition(
        positionChangeRequestDto));
  }

}
