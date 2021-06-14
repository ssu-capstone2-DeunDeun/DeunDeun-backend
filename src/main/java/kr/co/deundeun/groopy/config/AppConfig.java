package kr.co.deundeun.groopy.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableConfigurationProperties({AppProperties.class, SmsProperties.class})
@EnableScheduling
@EnableJpaAuditing
@Configuration
public class AppConfig {
}
