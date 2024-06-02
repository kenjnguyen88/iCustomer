package vn.esoft.platform.icustomer.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "roles_resources")
public class RoleResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity rRoleInstance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resource_id")
    private ResourceEntity resource;
//    @Column(name = "role_id", nullable = false)
//    private Long roleId;
//    @Column(name = "resource_id", nullable = false)
//    private Long resourceId;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

//    public Long getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(Long roleId) {
//        this.roleId = roleId;
//    }
//
//
//    public Long getResourceId() {
//        return resourceId;
//    }
//
//    public void setResourceId(Long resourceId) {
//        this.resourceId = resourceId;
//    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }


    public RoleEntity getrRoleInstance() {
        return rRoleInstance;
    }

    public void setrRoleInstance(RoleEntity rRoleInstance) {
        this.rRoleInstance = rRoleInstance;
    }

    public ResourceEntity getResource() {
        return resource;
    }

    public void setResource(ResourceEntity resource) {
        this.resource = resource;
    }


}
