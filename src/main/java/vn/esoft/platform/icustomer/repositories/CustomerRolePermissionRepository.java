package vn.esoft.platform.icustomer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.esoft.platform.icustomer.entities.CustomerRolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

@Repository
public interface CustomerRolePermissionRepository extends JpaRepository<CustomerRolePermissionEntity, Long> {

    @Query(value = "CALL esoft_authent.get_role_permissions_by_customerId(:customer_id);", nativeQuery = true)
    List<CustomerRolePermissionEntity> findByCustomerId(@Param("customer_id") Long customer_id);
}
