
package vn.esoft.platform.icustomer.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.Instant;
import java.util.*;

@Table(name = "customers")
@Entity
public class CustomerEntity extends BaseEntity implements UserDetails {
    @Serial
    private static final long serialVersionUID = 6833921540428414446L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customerInstance")
    private Set<CustomerRoleEntity> customerRoles;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public Map<String, List<String>> scope() {
        Map<String, List<String>> scope = new HashMap<>();
        List<RoleEntity> roleEntities = this.getCustomerRoles().stream().map(CustomerRoleEntity::getRole).toList();
        scope.put("roles", roleEntities.stream().map(e -> e.getName()).toList());

        List<String> permission = new ArrayList<>();
        List<String> urlResources = new ArrayList<>();
        List<String> nameResources = new ArrayList<>();
        for (RoleEntity roleEntity : roleEntities) {
            Set<RolePermissionEntity> rolePermissionEntities = roleEntity.getRolePermissions();
            for (RolePermissionEntity rolePermissionEntity : rolePermissionEntities) {
                permission.add(rolePermissionEntity.getPermission().getName());
            }
            Set<RoleResourceEntity> roleResources = roleEntity.getRoleResources();
            for (RoleResourceEntity roleResourceEntity : roleResources) {
                urlResources.add(roleResourceEntity.getResource().getUrl());
                nameResources.add(roleResourceEntity.getResource().getName());
            }
        }
        if (permission.size() != 0) {
            scope.put("permissions", permission);
        }
        if (urlResources.size() != 0) {
            scope.put("urlResources", urlResources);
            scope.put("nameResources", nameResources);
        }
        return scope;
    }

    public CustomerEntity() {
        this.createdAt();
    }

    public List<String> getRoles() {
        List<RoleEntity> roleEntities = this.getCustomerRoles().stream().map(CustomerRoleEntity::getRole).toList();
        return roleEntities.stream().map(e -> e.getName()).toList();
    }

    public List<String> getPermissions() {
        List<String> permission = new ArrayList<>();
        List<RoleEntity> roleEntities = this.getCustomerRoles().stream().map(customerRoleEntity -> customerRoleEntity.getcRoleInstance()).toList();
        for (RoleEntity roleEntity : roleEntities) {
            Set<RolePermissionEntity> rolePermissionEntities = roleEntity.getRolePermissions();
            for (RolePermissionEntity rolePermissionEntity : rolePermissionEntities) {
                permission.add(rolePermissionEntity.getPermission().getName());
            }
        }
        return permission;
    }

    public List<String> getResources() {
        List<String> urlResources = new ArrayList<>();
        List<RoleEntity> roleEntities = this.getCustomerRoles().stream().map(customerRoleEntity -> customerRoleEntity.getcRoleInstance()).toList();
        for (RoleEntity roleEntity : roleEntities) {
            Set<RoleResourceEntity> roleResources = roleEntity.getRoleResources();
            for (RoleResourceEntity roleResourceEntity : roleResources) {
                urlResources.add(roleResourceEntity.getResource().getUrl());
            }
        }
        return urlResources;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        this.getRoles().forEach(e->{
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + e.toString());
            authorityList.add(authority);
        });
        return authorityList;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public CustomerEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public CustomerEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerEntity setPassword(String password) {
        this.password = password;
        return this;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public Set<CustomerRoleEntity> getCustomerRoles() {
        return customerRoles;
    }

    public void setCustomerRoles(Set<CustomerRoleEntity> customerRoles) {
        this.customerRoles = customerRoles;
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

