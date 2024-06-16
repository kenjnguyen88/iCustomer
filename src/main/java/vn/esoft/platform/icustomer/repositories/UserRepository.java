package vn.esoft.platform.icustomer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.esoft.platform.icustomer.model.entities.CustomerEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByEmail(String email);
}
