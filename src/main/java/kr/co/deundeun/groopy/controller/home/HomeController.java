package kr.co.deundeun.groopy.controller.home;


import java.util.List;
import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.dto.common.page.Property;
import kr.co.deundeun.groopy.dto.home.ClubByCategoryDto;
import kr.co.deundeun.groopy.dto.home.HomeResponseDto;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HomeController {

  private final HomeService homeService;

  @GetMapping("/home")
  public ResponseEntity<HomeResponseDto> getHome(@RequestParam Property property){
    return ResponseEntity.ok(homeService.getHome(property));
  }

  @GetMapping("/clubs")
  public ResponseEntity<List<ClubByCategoryDto>> getAllClubs(@Me User user, @RequestParam CategoryType categoryType){
    return ResponseEntity.ok(homeService.getAllClubs(user, categoryType));
  }

  // 인기 게시물 전체보기는 postController에서 GET /posts 의 좋아요 순 소팅 사용
}

