package destiny.manager.destiny;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long>{

    @Transactional
    void deleteAll();
}