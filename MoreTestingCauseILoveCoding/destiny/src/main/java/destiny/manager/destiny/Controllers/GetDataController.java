package destiny.manager.destiny.Controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty.Access;

import destiny.manager.destiny.AccessToken;
import destiny.manager.destiny.AccessTokenResponse;
import destiny.manager.destiny.MyApiData;
import destiny.manager.destiny.MyApiResponse;
import destiny.manager.destiny.Repositorys.AccessTokenRepository;

@RestController
public class GetDataController {
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    //  @GetMapping("/user-data")
    //  public String getUserData(Model model){

    //     AccessToken accessToken = accessTokenRepository.findTopByOrderByIdDesc();

    //     if(accessToken == null){
    //         return "Error: Access Token Not Found";
    //     }
    //         String token = accessToken.getToken();
        
    //      HttpHeaders headers = new HttpHeaders();
    //      headers.set("Authorization", "Bearer" + token);
    //      HttpEntity<String> entity = new HttpEntity<>(headers);
    //      ResponseEntity<MyApiResponse> response = restTemplate.exchange("https://www.bungie.net/Platform/User/GetCurrentBungieAccount", HttpMethod.GET, entity, MyApiResponse.class);
    //      MyApiData apiData = response.getBody().getData();

    //      model.addAttribute("accountName", apiData.getAccountName());
    //      model.addAttribute("memberId", apiData.getMemberId());
    //      return "bungie-data";
    // }

    public class TokenController {
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

}
