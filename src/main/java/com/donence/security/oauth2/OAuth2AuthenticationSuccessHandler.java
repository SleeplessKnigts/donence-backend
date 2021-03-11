package com.donence.security.oauth2;

import com.donence.config.AppProperties;
import com.donence.security.TokenProvider;
import com.donence.security.services.UserDetailsImpl;
import com.donence.utils.CookieUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static com.donence.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private TokenProvider tokenProvider;

    private com.donence.config.AppProperties appProperties;

    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Autowired
    OAuth2AuthenticationSuccessHandler(TokenProvider tokenProvider, AppProperties appProperties,
            HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
        this.tokenProvider = tokenProvider;
        this.appProperties = appProperties;
        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            // throw new Exception(
            // "Sorry! We've got an Unauthorized Redirect URI and can't proceed with the
            // authentication");
            logger.error("Something is wrong with the authorized redirect uri");
        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        String token = tokenProvider.createToken(userDetailsImpl);
        String userType = "";
        for (GrantedAuthority role : userDetailsImpl.getAuthorities()) {
            switch (role.getAuthority()) {
                case "ROLE_ADMIN":
                    userType = "395cc606-30da-4789-9bd3-acc1add79ef9";
                    break;
                case "ROLE_USER":
                    userType = "8a6ee639-a7e6-456f-af12-2b714df5fecd";
                    break;
                case "ROLE_DRIVER":
                    userType = "2612bedd-ae65-4ed6-a8e1-8c7f868294d6";
                    break;
                default:
                    userType = "user";
                    break;
            }
        }

        return UriComponentsBuilder.fromUriString(targetUrl).queryParam("token", token).queryParam("userType", userType)
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);

        return appProperties.getOAuth2().getAuthorizedRedirectUris().stream().anyMatch(authorizedRedirectUri -> {
            URI authorizedURI = URI.create(authorizedRedirectUri);
            if (authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                    && authorizedURI.getPort() == clientRedirectUri.getPort()) {
                return true;
            }
            return false;
        });
    }
}
