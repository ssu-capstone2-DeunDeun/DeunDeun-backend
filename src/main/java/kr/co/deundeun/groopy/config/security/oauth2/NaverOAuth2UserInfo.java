package kr.co.deundeun.groopy.config.security.oauth2;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo{

    private Map<String, Object> response;

    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
        response = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public String getId() {
        return (String) response.get("id");
    }

    @Override
    public String getName() {
        return (String) response.get("name");
    }

    @Override
    public String getEmail() {
        return (String) response.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) response.get("profile_image");
    }

    @Override
    public String getPhoneNumber() {
        return (String) response.get("mobile");
    }
}
