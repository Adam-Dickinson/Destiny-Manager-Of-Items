package destiny.manager.destiny.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import destiny.manager.destiny.AccessToken;
import jakarta.transaction.Transactional;
@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long>{
    
    List<AccessToken> findAll();

    @Transactional
    void deleteAll();

    

    // create this getAccessTokenFromDatabase() method to get the token form the access_token table in the mysql database
}