package vn.esoft.platform.icustomer.model.entities;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@NamedStoredProcedureQuery(name = "esoft_authent.get_role_permissions_by_customerId", procedureName = "get_role_permissions_by_customerId", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_customer_id", type = Long.class) })
public class CustomerRolePermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "permission_id")
    private Long permissionId;

    @Column(name = "permission_name")
    private String permissionName;
}
