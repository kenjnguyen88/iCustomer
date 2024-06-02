package vn.esoft.platform.icustomer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.esoft.platform.icustomer.entities.CustomerEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByEmail(String email);
}
