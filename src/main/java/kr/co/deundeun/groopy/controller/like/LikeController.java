package kr.co.deundeun.groopy.controller.like;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.controller.like.dto.LikeResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/clubs/{clubName}/likes")
    public ResponseEntity<Void> likeClub(@Me User user, @PathVariable String clubName) {
        likeService.likeClub(user, clubName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/clubs/{clubName}/likes")
    public ResponseEntity<LikeResponseDto> getClubLike(@Me User user, @PathVariable String clubName){
        return ResponseEntity.ok(likeService.getClubLike(user, clubName));
    }

}
