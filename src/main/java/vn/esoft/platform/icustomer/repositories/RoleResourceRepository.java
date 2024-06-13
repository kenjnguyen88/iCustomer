package vn.esoft.platform.icustomer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.esoft.platform.icustomer.entities.RoleEntity;
import vn.esoft.platform.icustomer.entities.RoleResourceEntity;

@Repository
public interface RoleResourceRepository extends JpaRepository<RoleResourceEntity, Long> {
}
