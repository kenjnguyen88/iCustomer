
package vn.esoft.platform.icustomer.model.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Table(name = "customers")
@Entity
public class CustomerEntity extends BaseEntity implements UserDetails {

    @Serial
    private static final long serialVersionUID = 6833921540428414446L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private List<String> roles;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private List<String> permissions;

    public CustomerEntity() {
        this.createdAt();
    }

    public CustomerEntity(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
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

    public CustomerEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public CustomerEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
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

