package destiny.manager.destiny;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class OAuthController {

  @Autowired
  private AccessTokenRepository accessTokenRepository;

  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/callback")
  public void handleCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String token = request.getParameter("code");
    AccessToken accessToken = new AccessToken();
    accessToken.setToken(token);
    accessTokenRepository.save(accessToken);
    response.sendRedirect("/data");
  }

  @GetMapping("/data")
  public String data() {
    return "index";
  }
}
