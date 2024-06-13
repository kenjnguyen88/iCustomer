package vn.esoft.platform.icustomer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "resources_permissions")
public class ResourcePermissionEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -4086307387312029967L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "permission_id", nullable = false)
    private Long permissionId;
    @Column(name = "resource_id", nullable = false)
    private Long resourceId;

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
}
