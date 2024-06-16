package vn.esoft.platform.icustomer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.esoft.platform.icustomer.entities.SecurityTokenEntity;

import java.util.Optional;

@Repository
public interface SecurityTokenRepository extends JpaRepository<SecurityTokenEntity, Long> {
    Optional<SecurityTokenEntity> findByCustomerId(Long userId);

//    @Query(value = "SELECT * from security_tokens st \n" +
//            "WHERE st.customer_id = = ?1", nativeQuery = true)
//    Optional<SecurityTokenEntity> findByCustomerId(final Long customerId);
}
