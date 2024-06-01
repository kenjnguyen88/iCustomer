package vn.esfot.platform.icustomer.entities;

import jakarta.persistence.Column;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 4286536031236473476L;

    public String createdBy;


    public String updatedBy;

    public Instant createdAt;

    public Instant updatedAt;

    @Column(name = "created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "updated_by")
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "created_at", updatable = false)
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at")
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
