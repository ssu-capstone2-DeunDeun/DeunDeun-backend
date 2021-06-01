package kr.co.deundeun.groopy.config.security.oauth2;

import kr.co.deundeun.groopy.exception.OAuth2AuthenticationProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {

        // TODO 네이버 OAuth2UserInfo 클래스 생성 필요

        if (registrationId.equalsIgnoreCase(SocialProviderType.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(SocialProviderType.naver.toString())){
            return new NaverOAuth2UserInfo(attributes);
        }
        else {
            throw new OAuth2AuthenticationProcessingException(registrationId + "는 인증되지 않은 소셜 로그인입니다.");
        }

    }
}