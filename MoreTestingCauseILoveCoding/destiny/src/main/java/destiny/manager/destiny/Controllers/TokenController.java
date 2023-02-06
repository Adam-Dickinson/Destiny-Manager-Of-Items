package destiny.manager.destiny.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import destiny.manager.destiny.AccessTokenResponse;
import destiny.manager.destiny.AccessTokenService;

@RestController
public class TokenController {
    private final AccessTokenService accessTokenService;

    @Autowired
    public TokenController(AccessTokenService accessTokenService){
        this.accessTokenService = accessTokenService;
    }

    @GetMapping("/token")
    public String getToken(){
        return accessTokenService.getToken();
    }
        @Value("${bungie.clientId}")
        private String clientId;
        @Value("${bungie.clientSecret}")
        private String clientSecret;
        @Value("${bungie.tokenUrl}")
        private String tokenUrl;
      
        @PostMapping("/getToken")
        public AccessTokenResponse getToken(@RequestParam("token") String token) {
          RestTemplate restTemplate = new RestTemplate();
          HttpHeaders headers = new HttpHeaders();
          headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
          MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
          map.add("code", token);
          map.add("client_id", clientId);
          map.add("client_secret", clientSecret);
          map.add("grant_type", "authorization_code");
          HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
          AccessTokenResponse response = restTemplate.postForObject(tokenUrl, request, AccessTokenResponse.class);
         return response;
        }
}
 