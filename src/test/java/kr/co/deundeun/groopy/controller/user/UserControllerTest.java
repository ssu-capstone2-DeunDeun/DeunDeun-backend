package kr.co.deundeun.groopy.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.deundeun.groopy.config.DocumentationWithSecurity;
import kr.co.deundeun.groopy.config.security.UserPrincipal;
import kr.co.deundeun.groopy.config.security.oauth2.SocialProviderType;
import kr.co.deundeun.groopy.controller.user.dto.SignupRequestDto;
import kr.co.deundeun.groopy.controller.user.dto.UserResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
class UserControllerTest extends DocumentationWithSecurity {

    @MockBean
    private UserService userService;

    private MockMvc mvc;

    private User user;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {

        user = User.builder().email("test@gmail.com")
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

    @DisplayName("닉네임 중복을 확인한다.")
    @Test
    @WithMockUser
    void isDuplicatedNickname() throws Exception {
        String nickname = "test123";
        when(userService.isDuplicatedNickname(nickname)).thenReturn(true);

        mvc.perform(
                RestDocumentationRequestBuilders
                        .get("/users")
                        .param("nickname", "test123"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(UserDocumentation.isDuplicatedNickname());

    }

    @DisplayName("회원 가입을 한다.")
    @Test
    void registerUser() throws Exception {
        when(userService.signup(any(), any())).thenReturn(UserResponseDto.of(user));

        SignupRequestDto signupRequestDto = new SignupRequestDto("이름이요", "닉네임12", "010-2232");

        mvc.perform(post("/users")
                .content(new ObjectMapper().writeValueAsString(signupRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer USER_TOKEN"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(UserDocumentation.signup());
    }

    @DisplayName("유저 닉네임을 변경한다.")
    @Test
    void updateNickname() throws Exception {
        mvc.perform(RestDocumentationRequestBuilders
                .patch("/users/{nickname}", "새닉네임")
                .header("Authorization", "Bearer USER_TOKEN"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(UserDocumentation.changeNickname());
    }

    @DisplayName("윺저 정보를 확인한다.")
    @Test
    void getUserInfo() throws Exception {
        mvc.perform(RestDocumentationRequestBuilders
                .get("/users/{nickname}", "닉네임")
                .header("Authorization", "Bearer USER_TOKEN"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(UserDocumentation.getUserInfo());
    }

    @DisplayName("회원 가입 시 해시 태그를 추가한다.")
    @Test
    void addHashtags() throws Exception {

        List<String> hashtags = new ArrayList<>();
        hashtags.add("IT");
        hashtags.add("ART");

        mvc.perform(RestDocumentationRequestBuilders
                .post("/users/{nickname}/hashtags", "닉네임")
                .content(objectMapper.writeValueAsString(hashtags))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer USER_TOKEN"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(UserDocumentation.addHashtags());
    }

    @DisplayName("회원의 해시 태그 정보를 불러온다.")
    @Test
    void getHashtags() throws Exception {
        mvc.perform(RestDocumentationRequestBuilders
                .get("/users/{nickname}/hashtags", "닉네임")
                .header("Authorization", "Bearer USER_TOKEN"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(UserDocumentation.getHashtags());
    }
}