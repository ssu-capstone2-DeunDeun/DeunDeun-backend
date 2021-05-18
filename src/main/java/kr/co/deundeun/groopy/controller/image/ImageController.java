package kr.co.deundeun.groopy.controller.image;

import java.util.List;
import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class ImageController {

  private final S3Uploader s3Uploader;

  @PostMapping("/image")
  public List<String> test(@Me User user,  List<MultipartFile> multipartFiles){
    return s3Uploader.uploadImageList(user.getId(), multipartFiles);
  }
}
