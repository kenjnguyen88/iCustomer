package vn.esfot.platform.icustomer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.esfot.platform.icustomer.entities.SecurityTokenEntity;

@Repository
public interface SecurityTokenRepository extends CrudRepository<SecurityTokenEntity, Long> {
}
