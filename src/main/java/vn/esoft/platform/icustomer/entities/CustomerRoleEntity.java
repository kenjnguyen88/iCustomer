package vn.esoft.platform.icustomer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers_roles")
public class CustomerRoleEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -2910747967607961138L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "customer_id")
    private Long customerId;

    @JoinColumn(name = "role_id")
    private Long roleId;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Override
    public void createdAt() {
        this.createdAt = Instant.now();
        this.createdBy = "sys";
    }

    @Override
    public void updatedAt() {
        this.updatedAt = Instant.now();
        this.updatedBy = "sys";
    }

    public CustomerRoleEntity(Long customerId, Long roleId) {
        this.customerId = customerId;
        this.roleId = roleId;
        this.createdAt();
    }
}
