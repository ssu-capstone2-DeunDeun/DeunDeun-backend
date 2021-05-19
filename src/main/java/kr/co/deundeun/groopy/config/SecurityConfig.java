package kr.co.deundeun.groopy.config;

import kr.co.deundeun.groopy.config.security.*;
import kr.co.deundeun.groopy.config.security.oauth2.CustomOAuth2UserService;
import kr.co.deundeun.groopy.config.security.oauth2.OAuth2AuthenticationFailureHandler;
import kr.co.deundeun.groopy.config.security.oauth2.OAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()

                .authorizeRequests()
                .antMatchers("/",
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js").permitAll()
                .antMatchers("/auth/**", "/oauth2/**", "/jwt/refresh/**").permitAll()
                .antMatchers("/user/**").authenticated()
                .and()

                .oauth2Login()
                    .authorizationEndpoint()
                    .baseUri("/oauth2/authorize")
                    .authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository)
                .and()
                    .redirectionEndpoint()
                    .baseUri("/oauth2/callback/*")
                .and()
                    // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당합니다.
                    .userInfoEndpoint()

                    // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록합니다.
                    // 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있습니다.
                    .userService(customOAuth2UserService)

                .and()
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .failureHandler(oAuth2AuthenticationFailureHandler);

        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web){
        web
                .ignoring()
                .antMatchers("/v2/api-docs", "/swagger-resources/**",
                        "/swagger-ui.html", "/webjars/**", "/swagger/**", "/h2-console/**");

    }
}
