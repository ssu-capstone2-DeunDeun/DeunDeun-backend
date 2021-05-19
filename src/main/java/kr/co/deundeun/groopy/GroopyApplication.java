package kr.co.deundeun.groopy;

import kr.co.deundeun.groopy.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class GroopyApplication {

  public static void main(String[] args) {
    SpringApplication.run(GroopyApplication.class, args);
  }

}
