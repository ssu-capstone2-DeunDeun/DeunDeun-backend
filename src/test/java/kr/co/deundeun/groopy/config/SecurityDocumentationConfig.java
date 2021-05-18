package kr.co.deundeun.groopy.config;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@Configuration
@ComponentScan(basePackages = "kr.co.deundeun.groopy.config")
@MockBean(JpaMetamodelMappingContext.class)
public class SecurityDocumentationConfig {
}
