package destiny.manager.destiny.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import destiny.manager.destiny.AccessToken;
import jakarta.transaction.Transactional;
@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long>{
    
    AccessToken findByToken(String token);

    @Transactional
    void deleteAll();

    //in a @Repository class called AccessTokenRepository create a method that will retrieve a token from a database table which can then be used in my GetDataController and used for an api call

    

    
}