package kr.co.deundeun.groopy.util;



import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class S3Uploader {

  private AmazonS3 s3Client;

  @Value("${cloud.aws.credentials.accessKey}")
  private String accessKey;

  @Value("${cloud.aws.credentials.secretKey}")
  private String secretKey;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  @Value("${cloud.aws.region.static}")
  private String region;

  @PostConstruct
  public void setS3Client() {
    AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

    s3Client = AmazonS3ClientBuilder.standard()
                                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                                    .withRegion(this.region)
                                    .build();
  }

  public List<String> uploadImageList(Long userId, List<MultipartFile> multipartFiles) {

    return multipartFiles.stream()
                         .map(multipartFile -> uploadImage(userId, multipartFile))
                         .collect(Collectors.toList());
  }

  public String uploadImage(Long userId, MultipartFile multipartFile) {

    File uploadFile = convertToFileObject(multipartFile);
    String uploadImageUrl = putS3(userId, uploadFile);
    removeNewFile(uploadFile);
    return uploadImageUrl;

    //    ObjectMetadata objectMetadata = new ObjectMetadata();
//    objectMetadata.setContentType(file.getContentType());
//
//    try (InputStream inputStream = file.getInputStream()) {
//      PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, uniqueFileName, inputStream,
//          objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead);
//      s3Client.putObject(putObjectRequest);
//    } catch (IOException e) {
//      throw new CustomException(ImageUploadErrorCodeType.FILE_IO_ERROR);
//  }
//    return s3Client.getUrl(bucket, uniqueFileName).toString();
  }

  private void removeNewFile(File uploadFile) {
    if (uploadFile.delete()) {
//      log.info("파일이 삭제되었습니다.");
    } else {
//      log.info("파일이 삭제되지 못했습니다.");
    }
  }

  private String putS3(Long userId, File uploadFile) {
    String uniqueFileName =  createUniqueFileName(userId, uploadFile.getName());
    PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, uniqueFileName, uploadFile).withCannedAcl(
        CannedAccessControlList.PublicRead);
    s3Client.putObject(putObjectRequest);
    return s3Client.getUrl(bucket, uniqueFileName).toString();
  }

  private File convertToFileObject(MultipartFile file) {

    File convertFile = new File(file.getOriginalFilename());

    try  {
      if (!convertFile.createNewFile()){
        throw new IllegalArgumentException();
      }
      FileOutputStream fos = new FileOutputStream(convertFile);
      fos.write(file.getBytes());
      fos.close();
    } catch (IOException e) {
//      파일 입출력 에러
    } catch (IllegalArgumentException e){
//      기존 존재하는 파일 => createNewFile 에서 발생 처리
    }

    return convertFile;
  }

  private String createUniqueFileName(Long userId, String originalFilename) {
    String folder_delimiter = "/";
    String file_delimiter = "-";
    return userId.toString() + folder_delimiter + LocalDateTime.now() + file_delimiter + originalFilename;
  }
}

