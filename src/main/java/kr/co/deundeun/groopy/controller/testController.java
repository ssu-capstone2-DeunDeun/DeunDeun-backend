package kr.co.deundeun.groopy.controller;

import kr.co.deundeun.groopy.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class testController {

  private final S3Uploader s3Uploader;

  @PostMapping("/test")
  public String test(MultipartFile multipartFile){
    return s3Uploader.upload(1L, multipartFile);
  }
}
