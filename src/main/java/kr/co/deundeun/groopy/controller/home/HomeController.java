package kr.co.deundeun.groopy.controller.home;


import kr.co.deundeun.groopy.controller.common.page.Property;
import kr.co.deundeun.groopy.controller.home.dto.HomeResponseDto;
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

}

