package com.example.appid;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
	
	private static Logger Log = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	OAuth2AuthorizedClientService authclientService;
    
    @RequestMapping("/user")
    public Principal user(@AuthenticationPrincipal Principal principal) {
        // Principal holds the logged in user information.
        // Spring automatically populates this principal object after login.
        return principal;
    }
    
    @RequestMapping("/userInfo")
    public String userInfo(@AuthenticationPrincipal Principal principal) {
    	
    	final OAuth2AuthenticationToken oAuth2Authentication = (OAuth2AuthenticationToken) principal;
        Log.info("{}",oAuth2Authentication);
        OAuth2AuthorizedClient client = authclientService.loadAuthorizedClient(oAuth2Authentication.getAuthorizedClientRegistrationId(), oAuth2Authentication.getName());
        Log.info("Token Value : "+ client.getAccessToken().getTokenValue());
        return oAuth2Authentication.getDetails().toString();
//    	OAuth2AuthorizedClient client = authclientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
//        LOG.info("Token Value : "+ client.getAccessToken().getTokenValue());
//        return String.valueOf(principal);
    }
}
