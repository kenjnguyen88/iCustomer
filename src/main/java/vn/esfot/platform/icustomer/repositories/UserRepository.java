package vn.esfot.platform.icustomer.repositories;

import vn.esfot.platform.icustomer.entities.customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<customer, Integer> {
    Optional<customer> findByEmail(String email);
}
