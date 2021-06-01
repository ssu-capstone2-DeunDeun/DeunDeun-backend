package kr.co.deundeun.groopy.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {

    private String accessKey;
    private String secretKey;
    private String serviceId;
    private String from;

}
