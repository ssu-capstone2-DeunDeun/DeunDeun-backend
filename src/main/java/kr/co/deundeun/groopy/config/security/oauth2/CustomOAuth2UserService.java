package kr.co.deundeun.groopy.config.security.oauth2;

import kr.co.deundeun.groopy.config.security.UserPrincipal;
import kr.co.deundeun.groopy.dao.UserRepository;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.OAuth2AuthenticationProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory
                .getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());

        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (!user.getSocialProvider()
                    .equals(SocialProviderType.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException(
                        user.getSocialProvider().toString() + " 로 등록한 아이디가 있습니다");
            }

        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {

        User user = User.builder()
                .socialProvider(SocialProviderType.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
                .socialId(oAuth2UserInfo.getId())
                .email(oAuth2UserInfo.getEmail())
                .build();

        return userRepository.save(user);
    }

}
