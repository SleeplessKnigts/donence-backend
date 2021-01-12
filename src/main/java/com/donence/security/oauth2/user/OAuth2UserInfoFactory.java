package com.donence.security.oauth2.user;

import java.util.Map;
import javax.security.sasl.AuthenticationException;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes)
            throws AuthenticationException {
        if (registrationId.equalsIgnoreCase("google")) {
            return new GoogleOAuth2UserInfo(attributes);
        }
        // else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
        // return new FacebookOAuth2UserInfo(attributes);
        // }
        else {
            throw new AuthenticationException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
