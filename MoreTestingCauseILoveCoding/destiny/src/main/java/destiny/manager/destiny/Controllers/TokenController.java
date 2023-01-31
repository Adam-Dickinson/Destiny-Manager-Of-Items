package destiny.manager.destiny.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
 