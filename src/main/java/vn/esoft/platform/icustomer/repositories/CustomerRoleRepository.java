package vn.esoft.platform.icustomer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.esoft.platform.icustomer.entities.CustomerRoleEntity;
import vn.esoft.platform.icustomer.entities.PermissionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRoleRepository extends JpaRepository<CustomerRoleEntity, Long> {

    @Query(value = "SELECT id, customer_id, role_id, created_at, created_by, updated_at, updated_by FROM customers_roles cr WHERE cr.customer_id = ?1", nativeQuery = true)
    Optional<List<CustomerRoleEntity>> findCustomerId(final Long customerId);
    @Query(value = "SELECT customer_id, role_id FROM customers_roles cr WHERE cr.customer_id = ?1 and cr.role_id = ?2", nativeQuery = true)
    Optional<CustomerRoleEntity> findByCustomerRole(final Long customerId, final Long roleId);
}
