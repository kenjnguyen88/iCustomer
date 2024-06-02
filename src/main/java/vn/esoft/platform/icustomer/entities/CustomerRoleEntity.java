package vn.esoft.platform.icustomer.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "customers_roles")
public class CustomerRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long customerRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerInstance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity cRoleInstance;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public CustomerEntity getCustomerInstance() {
        return customerInstance;
    }

    public void setCustomerInstance(CustomerEntity customerInstance) {
        this.customerInstance = customerInstance;
    }


    public Long getCustomerRoleId() {
        return customerRoleId;
    }

    public void setCustomerRoleId(Long customerRoleId) {
        this.customerRoleId = customerRoleId;
    }

    public RoleEntity getRole() {
        return cRoleInstance;
    }

    public void setRole(RoleEntity role) {
        this.cRoleInstance = role;
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

    public RoleEntity getcRoleInstance() {
        return cRoleInstance;
    }

    public void setcRoleInstance(RoleEntity cRoleInstance) {
        this.cRoleInstance = cRoleInstance;
    }
}
