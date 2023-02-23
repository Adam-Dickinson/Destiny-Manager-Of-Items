package destiny.manager.destiny.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import destiny.manager.destiny.BungieAccessToken;

public interface BungieAccessTokenRepo extends JpaRepository<BungieAccessToken, Long>{
    
}
