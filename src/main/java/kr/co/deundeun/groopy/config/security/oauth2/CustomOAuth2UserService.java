package kr.co.deundeun.groopy.config.security.oauth2;

import java.util.Map;
import kr.co.deundeun.groopy.config.security.UserPrincipal;
import kr.co.deundeun.groopy.dao.UserRepository;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.OAuth2AuthenticationProcessingException;
import kr.co.deundeun.groopy.helper.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();
        try {
            return processOAuth2User(registrationId, oAuth2User);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(String registrationId, OAuth2User oAuth2User) {

        Map<String, Object> attributes = oAuth2User.getAttributes();

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);

        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) { // naver구현 안해서 null 일수도 있다고 나오는거
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user = userOptional.orElseGet(() -> registerNewUser(registrationId, oAuth2UserInfo));

        return UserPrincipal.create(user, attributes);

        //        if (userOptional.isPresent()) {
//            user = userOptional.get();
//            if (!user.getSocialProvider()
//                    .equals(SocialProviderType.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
//                throw new OAuth2AuthenticationProcessingException(
//                        user.getSocialProvider().toString() + " 로 등록한 아이디가 있습니다");
//            }
//
//        } else {
//            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
//        }
//
//        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    @Transactional
    public User registerNewUser(String registrationId, OAuth2UserInfo oAuth2UserInfo) {

        User user = User.builder()
                .socialProvider(SocialProviderType.valueOf(registrationId))
                .socialId(oAuth2UserInfo.getId())
                .email(oAuth2UserInfo.getEmail())
                .phoneNumber(oAuth2UserInfo.getPhoneNumber())
                .build();

        return userRepository.save(user);
    }
}
