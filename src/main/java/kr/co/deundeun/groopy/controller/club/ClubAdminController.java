package kr.co.deundeun.groopy.controller.club;

import kr.co.deundeun.groopy.service.ClubAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/clubs/{clubName}/admins")
@RestController
public class ClubAdminController {

    private final ClubAdminService clubAdminService;

    @PostMapping
    public void giveAdminRole(@PathVariable String clubName, @RequestParam Long userId){
        clubAdminService.giveAdminRole(clubName, userId);
    }

}
