package destiny.manager.destiny.Repositorys;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import destiny.manager.destiny.AccessToken;
import jakarta.transaction.Transactional;
@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long>{
    
   // @Query("SELECT t from AccessToken t ")
    //@Query(value="SELECT * from  access_token where token =:token", nativeQuery = true)
    AccessToken findByToken(String token);

   // @Transactional
   // void deleteAll();

    

    

    
}