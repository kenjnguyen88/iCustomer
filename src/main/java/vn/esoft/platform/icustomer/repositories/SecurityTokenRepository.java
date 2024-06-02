package vn.esoft.platform.icustomer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.esoft.platform.icustomer.entities.SecurityTokenEntity;

@Repository
public interface SecurityTokenRepository extends CrudRepository<SecurityTokenEntity, Long> {
}
