package destiny.manager.destiny.Controllers;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import destiny.manager.destiny.AccessToken;
import destiny.manager.destiny.BungieAccessToken;
import destiny.manager.destiny.MyApiData;
import destiny.manager.destiny.MyApiResponse;
import destiny.manager.destiny.MyService;
import destiny.manager.destiny.Repositorys.AccessTokenRepository;
import destiny.manager.destiny.Repositorys.BungieAccessTokenRepo;

@RestController
public class GetDataController {
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Autowired
    private BungieAccessTokenRepo bungieAccessTokenRepo;

    @Autowired
    private MyService myService;

      @GetMapping("/user-info")
      public String getUserData(Model model){

         AccessToken accessToken = accessTokenRepository.findTopByOrderByIdDesc();

         if(accessToken == null){
             return "Error: Access Token Not Found";
         }
             String token = accessToken.getToken();
        
          HttpHeaders headers = new HttpHeaders();
          headers.set("Authorization", "Bearer" + token);
          HttpEntity<String> entity = new HttpEntity<>(headers);
          ResponseEntity<MyApiResponse> response = restTemplate.exchange("https://www.bungie.net/Platform/User/GetCurrentBungieAccount", HttpMethod.GET, entity, MyApiResponse.class);
          MyApiData apiData = response.getBody().getData();

          model.addAttribute("accountName", apiData.getAccountName());
          model.addAttribute("memberId", apiData.getMemberId());
          return "bungie-data";
     }

     @GetMapping("/user-data")
     public String convertToken(@RequestParam("accessToken") String token){

        AccessToken accessToken = accessTokenRepository.findTopByOrderByIdDesc();

         if(accessToken == null)
         {
             return "Error: Access Token Not Found";
         }
             String access = accessToken.getToken();
        
        myService.convertOAuthTokenToBungieAccessToken(access);
        return("Token Saved");
        
     }
  
}
