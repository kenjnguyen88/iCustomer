package vn.esoft.platform.icustomer.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.Instant;

@Getter
@Setter
@Table(name = "resources")
@Entity
public class ResourceEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 6252723691275106655L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

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
