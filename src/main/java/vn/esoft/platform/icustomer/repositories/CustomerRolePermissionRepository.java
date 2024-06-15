package vn.esoft.platform.icustomer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.esoft.platform.icustomer.entities.CustomerRolePermissionEntity;

import java.sql.ResultSet;
import java.util.List;

@Repository
public interface CustomerRolePermissionRepository extends JpaRepository<CustomerRolePermissionEntity, Long> {

    @Query(value = "CALL esoft_authent.get_role_permissions_by_customerId(:customer_id);", nativeQuery = true)
    List<CustomerRolePermissionEntity> findByCustomerId(@Param("customer_id") Long customer_id);

    @Procedure(value = "esoft_authent.get_role_permissions_by_customerId")
    ResultSet getByCustomerId(Long customerId);
}
