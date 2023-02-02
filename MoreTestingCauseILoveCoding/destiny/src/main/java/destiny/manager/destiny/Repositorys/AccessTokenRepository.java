package destiny.manager.destiny.Repositorys;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import destiny.manager.destiny.AccessToken;
import jakarta.transaction.Transactional;
@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long>{
    
   
    //@Query(value="SELECT * from  access_token where token =:token", nativeQuery = true)
   //AccessToken findByToken(AccessToken accessToken);

   AccessToken findTopByOrderByIdDesc();

   // @Transactional
   // void deleteAll();

    

    

    
}