package kr.co.deundeun.groopy.controller.clubRecruit;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.deundeun.groopy.config.DocumentationWithSecurity;
import kr.co.deundeun.groopy.config.SecurityConfig;
import kr.co.deundeun.groopy.config.security.HttpCookieOAuth2AuthorizationRequestRepository;
import kr.co.deundeun.groopy.config.security.UserPrincipal;
import kr.co.deundeun.groopy.config.security.oauth2.CustomOAuth2UserService;
import kr.co.deundeun.groopy.config.security.oauth2.SocialProviderType;
import kr.co.deundeun.groopy.controller.user.UserDocumentation;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.ClubRecruitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClubRecruitController.class)
class ClubRecruitControllerTest extends DocumentationWithSecurity {

    @MockBean
    private ClubRecruitService clubRecruitService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .apply(springSecurity())
                .build();
    }


    @Test
    void addRecruit() throws Exception{
    }

    @Test
    void getRecruits() throws Exception {

    }

    @Test
    void getRecruit() throws Exception{
    }

    @Test
    void updateRecruit() throws Exception{
    }

    @Test
    void deleteRecruit() throws Exception{
    }
}