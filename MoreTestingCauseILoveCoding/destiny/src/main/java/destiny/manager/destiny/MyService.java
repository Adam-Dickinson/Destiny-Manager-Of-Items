package destiny.manager.destiny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    
    @Autowired
    private BungieAccessTokenService bungieAccessTokenService;

    public void convertOAuthTokenToBungieAccessToken(String accessToken){
        String bungieAccessToken = bungieAccessTokenService.getBungieAccessToken(accessToken);
    }
}
