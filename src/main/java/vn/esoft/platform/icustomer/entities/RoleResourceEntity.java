package vn.esoft.platform.icustomer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "roles_resources")
public class RoleResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "role_id")
    private Long roleId;

    @JoinColumn(name = "resource_id")
    private Long resourceId;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    private void updatedAt(){
        this.updatedBy = "sys";
        this.updatedAt = Instant.now();
    }

    public RoleResourceEntity(Long roleId, Long resourceId) {
        this.roleId = roleId;
        this.resourceId = resourceId;
        this.updatedAt();
    }
}
