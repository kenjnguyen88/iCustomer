package vn.esoft.platform.icustomer.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Table(name = "roles")
@Entity
public class RoleEntity extends BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cRoleInstance")
    private Set<CustomerRoleEntity> customerRoles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pRoleInstance")
    private Set<RolePermissionEntity> rolePermissions;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "rRoleInstance")
    private Set<RoleResourceEntity> roleResources;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public Set<CustomerRoleEntity> getCustomerRoles() {
        return customerRoles;
    }

    public void setCustomerRoles(Set<CustomerRoleEntity> customerRoles) {
        this.customerRoles = customerRoles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Set<RolePermissionEntity> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermissionEntity> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public Set<RoleResourceEntity> getRoleResources() {
        return roleResources;
    }

    public void setRoleResources(Set<RoleResourceEntity> roleResources) {
        this.roleResources = roleResources;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
