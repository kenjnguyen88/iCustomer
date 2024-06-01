package vn.esfot.platform.icustomer.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.time.Instant;

@Table(name = "security_tokens")
@Entity
public class SecurityTokenEntity extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 7772311196128235344L;

    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(nullable = false)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String customerId;

    @Column(name = "access_token", nullable = false)
    private String accessToken;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "access_token_expires_at", nullable = false)
    private Instant accessTokenExpiresAt;


    @Column(name = "refresh_token_expires_at", nullable = false)
    private Instant refreshTokenExpiresAt;

    @Column(name = "issued_at", nullable = false)
    private Instant issued_at;

    @Override
    public String getCreatedBy() {
        return super.getCreatedBy();
    }

    @Override
    public void setCreatedBy(String createdBy) {
        super.setCreatedBy(createdBy);
    }
}
