package destiny.manager.destiny;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class OAuthController {

  @Autowired
  private AccessTokenRepository accessTokenRepository;

  @GetMapping("/callback")
  public void handleCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String token = request.getParameter("code");
    AccessToken accessToken = new AccessToken();
    accessToken.setToken(token);
    accessTokenRepository.save(accessToken);
    response.sendRedirect("/welcome");
  }

  @GetMapping("/welcome")
    public String home(){
        return "home";
    }
}
