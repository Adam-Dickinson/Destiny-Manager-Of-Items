package destiny.manager.destiny;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import destiny.manager.destiny.Repositorys.AccessTokenRepository;
import destiny.manager.destiny.Repositorys.BungieAccessTokenRepo;

@Service
public class BungieAccessTokenService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BungieAccessTokenRepo bungieAccessTokenRepo;

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    public String getBungieAccessToken(String accessToken) {
        
        //Create the request headers with the OAuth2 token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("42774", "XaWYHVjA5aFNa4PwbMomwzlvWE.9xagzbtDVRdiiG-0");

        //Create the request body with OAuth2 token
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        AccessToken oAuthAccessToken = accessTokenRepository.findTopByOrderByIdDesc();

         if(oAuthAccessToken == null)
         {
             return "Error: Access Token Not Found";
         }
             String token = oAuthAccessToken.getToken();

        body.add("grant_type", "authorization_code");
        body.add("code", token);

        //create request entity
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        // Send the request and get the response entity
        ResponseEntity<Map> responseEntity = restTemplate.exchange(
                "https://www.bungie.net/platform/app/oauth/token/",
                HttpMethod.POST,
                requestEntity,
                Map.class
        );

        // Parse the response and extract the access token, token type, and expiration time
        String bungieAccessToken = responseEntity.getBody().get("access_token").toString();
        String tokenType = responseEntity.getBody().get("token_type").toString();
        Long expiresIn = Long.parseLong(responseEntity.getBody().get("expires_in").toString());

        // Create a new instance of BungieOAuthToken and set its fields to the parsed values
        BungieAccessToken bungieOAuthToken = new BungieAccessToken();
        bungieOAuthToken.setAccessToken(accessToken);
        bungieOAuthToken.setTokenType(tokenType);
        bungieOAuthToken.setExpiresIn(expiresIn);

        // Save the BungieOAuthToken object to the database
        bungieAccessTokenRepo.save(bungieOAuthToken);

        // Return the access token
        return accessToken;
        
    }
}
