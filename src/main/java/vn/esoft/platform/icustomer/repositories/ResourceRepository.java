package vn.esoft.platform.icustomer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.esoft.platform.icustomer.entities.ResourceEntity;
import vn.esoft.platform.icustomer.entities.RoleEntity;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {
}