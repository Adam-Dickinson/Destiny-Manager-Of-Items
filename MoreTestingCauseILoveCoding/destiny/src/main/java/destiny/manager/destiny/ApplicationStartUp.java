package destiny.manager.destiny;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartUp implements ApplicationListener<ContextRefreshedEvent>{
    
    private final AccessTokenRepository accessTokenRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        accessTokenRepository.deleteAll();
    }
}
