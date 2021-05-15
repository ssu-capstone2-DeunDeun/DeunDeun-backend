package kr.co.deundeun.groopy.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.deundeun.groopy.config.UserPrincipal;
import kr.co.deundeun.groopy.config.security.oauth2.SocialProviderType;
import kr.co.deundeun.groopy.controller.user.dto.SignupRequestDto;
import kr.co.deundeun.groopy.controller.user.dto.UserResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                      RestDocumentationContextProvider restDocumentation) {

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
    void registerUser() throws Exception {
        when(userService.signup(any(), any())).thenReturn(new UserResponseDto("asd", "테스트 통과"));

        SignupRequestDto signupRequestDto = new SignupRequestDto("이름이요", "닉네임12", "010-2232");

        mvc.perform(post("/user/signup")
                .content(new ObjectMapper().writeValueAsString(signupRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer USER_TOKEN"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void changeNickname() throws Exception{
        mvc.perform(patch("/user/nickname")
                .content(new ObjectMapper().writeValueAsString("newNickname"))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer USER_TOKEN"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}