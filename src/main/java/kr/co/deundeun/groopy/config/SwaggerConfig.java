package kr.co.deundeun.groopy.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private static final String DEFAULT_TITLE = "GROOPY PROJECT - ";
  private static final String CONTROLLER_PACKAGE_NAME = "kr.co.deundeun.groopy.controller";
  private static final String ALARM_PACKAGE =  setPackageName(".alarm");
  private static final String CLUB_PACKAGE =  setPackageName(".club");
  private static final String CLUB_APPLY_PACKAGE =  setPackageName(".clubApply");
  private static final String CLUB_APPLY_FORM_PACKAGE =  setPackageName(".clubApplyForm");
  private static final String CLUB_RECRUIT_PACKAGE =  setPackageName(".clubRecruit");
  private static final String COMMENT_PACKAGE =  setPackageName(".comment");
  private static final String HASHTAG_PACKAGE =  setPackageName(".hashtag");
  private static final String HOME_PACKAGE =  setPackageName(".home");
  private static final String IMAGE_PACKAGE =  setPackageName(".image");
  private static final String JWT_PACKAGE =  setPackageName(".jwt");
  private static final String LIKE_PACKAGE =  setPackageName(".like");
  private static final String POST_PACKAGE =  setPackageName(".post");
  private static final String USER_PACKAGE =  setPackageName(".user");

  private String groupName;

  private static String setPackageName(String packagePath){
    return CONTROLLER_PACKAGE_NAME + packagePath;
  }

  private ApiInfo apiInfo(String description) {
    return new ApiInfoBuilder()
        .title("Groopy API DOCS")
        .description(description)
        .build();
  }

  private Docket getDocket(String groupName, String basePackage) {
    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .groupName(groupName)
        .select()
        .apis(RequestHandlerSelectors.basePackage(basePackage))
        .build()
        .apiInfo(apiInfo(DEFAULT_TITLE + groupName));
  }

  @Bean
  public Docket alarmApiDocket() {
    groupName = "ALARM";
    return getDocket(groupName, ALARM_PACKAGE);
  }

  @Bean
  public Docket clubApiDocket() {
    groupName = "CLUB";
    return getDocket(groupName, CLUB_PACKAGE);
  }

  @Bean
  public Docket clubApplyApiDocket() {
    groupName = "CLUB_APPLY";
    return getDocket(groupName, CLUB_APPLY_PACKAGE);
  }

  @Bean
  public Docket clubApplyFormApiDocket() {
    groupName = "CLUB_APPLY_FORM";
    return getDocket(groupName, CLUB_APPLY_FORM_PACKAGE);
  }

  @Bean
  public Docket clubRecruitApiDocket() {
    groupName = "CLUB_RECRUIT";
    return getDocket(groupName, CLUB_RECRUIT_PACKAGE);
  }

  @Bean
  public Docket commentApiDocket() {
    groupName = "COMMENT";
    return getDocket(groupName, COMMENT_PACKAGE);
  }

  @Bean
  public Docket hashtagApiDocket() {
    groupName = "HASHTAG";
    return getDocket(groupName, HASHTAG_PACKAGE);
  }

  @Bean
  public Docket homeApiDocket() {
    groupName = "HOME";
    return getDocket(groupName, HOME_PACKAGE);
  }

  @Bean
  public Docket imageApiDocket() {
    groupName = "IMAGE";
    return getDocket(groupName, IMAGE_PACKAGE);
  }

  @Bean
  public Docket jwtApiDocket() {
    groupName = "JWT";
    return getDocket(groupName, JWT_PACKAGE);
  }

  @Bean
  public Docket likeApiDocket() {
    groupName = "LIKE";
    return getDocket(groupName, LIKE_PACKAGE);
  }

  @Bean
  public Docket postApiDocket() {
    groupName = "POST";
    return getDocket(groupName, POST_PACKAGE);
  }

  @Bean
  public Docket userApiDocket() {
    groupName = "USER";
    return getDocket(groupName, USER_PACKAGE);
  }

}