package vn.esoft.platform.icustomer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.esoft.platform.icustomer.entities.ResourceEntity;
import vn.esoft.platform.icustomer.entities.ResourcePermissionEntity;

@Repository
public interface ResourcePermissionRepository extends JpaRepository<ResourcePermissionEntity, Long> {
}
