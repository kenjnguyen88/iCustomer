package vn.esoft.platform.icustomer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.esoft.platform.icustomer.entities.CustomerRoleEntity;
import vn.esoft.platform.icustomer.entities.PermissionEntity;

@Repository
public interface CustomerRoleRepository extends JpaRepository<CustomerRoleEntity, Long> {
}
