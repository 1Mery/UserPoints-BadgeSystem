package demo.userservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "name", nullable = false)
    private String userName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "total_points", nullable = false)
    private int totalPoints = 0; // Varsayılan 0

    @Column(name = "total_budget", nullable = false)
    private BigDecimal totalBudget = BigDecimal.ZERO; // Varsayılan 0 para

    //getter ve setter lombok ile
}

