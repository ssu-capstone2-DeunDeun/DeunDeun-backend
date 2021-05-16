package kr.co.deundeun.groopy.controller.clubRecruit;

import kr.co.deundeun.groopy.config.security.UserPrincipal;
import kr.co.deundeun.groopy.config.security.oauth2.SocialProviderType;
import kr.co.deundeun.groopy.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ClubRecruitControllerTest {

    private MockMvc mvc;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext) {

        User user = User.builder().email("test@gmail.com")
                .socialProvider(SocialProviderType.google)
                .socialId("testId")
                .build();
        UserPrincipal userPrincipal = UserPrincipal.create(user);

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities()));

        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(springSecurity())
                .build();
    }

    @Test
    void addRecruit() throws Exception {

        String url = "/recruits";

        //when
        ResultActions resultActions = this.mvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\":\"jojoldu\", \"requestDateTime\":\"2018-12-15T10:00:00\"}"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("post 성공")))
                .andDo(print() );
    }
}