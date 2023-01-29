package destiny.manager.destiny;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class BungieController {

    @GetMapping(" ")
    public String index(){
        return "index";
    }

    @GetMapping("/authorize")
    public RedirectView authorize() {
        String clientId = "42774";
        String redirectUri = "http://localhost:8080";
        String bungieAuthUrl = "https://www.bungie.net/en/OAuth/Authorize?client_id=" + clientId + "&response_type=code&redirect_uri=" + redirectUri + "/callback";

        return new RedirectView(bungieAuthUrl);
    }

}
