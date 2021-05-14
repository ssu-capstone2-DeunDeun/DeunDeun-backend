package kr.co.deundeun.groopy.controller.user;

import kr.co.deundeun.groopy.config.UserPrincipal;
import kr.co.deundeun.groopy.config.security.oauth2.SocialProviderType;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
class UserControllerTest {

    @MockBean
    private UserService userService;

    private MockMvc mvc;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation){

        User user = User.builder().email("asdasd@gmail.com")
                .socialProvider(SocialProviderType.google)
                .socialId("sadsdaasd")
                .build();
        UserPrincipal userPrincipal = UserPrincipal.create(user);

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities()));

        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .apply(springSecurity())
                .build();
    }



    @Test
    @WithMockUser
    void isDuplicatedNickname() throws Exception {
        String nickname = "test123";
        when(userService.isDuplicatedNickname(nickname)).thenReturn(true);

        mvc.perform(get("/user/nickname")
                .content(nickname))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void registerUser() {

    }
}