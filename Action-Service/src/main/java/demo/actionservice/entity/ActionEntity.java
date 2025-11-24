package demo.actionservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "actions")
@Getter
@Setter
public class ActionEntity {

    @Id
    @GeneratedValue
    UUID actionId;

    UUID userId;

    @Enumerated(EnumType.STRING)
    ActionType actionType;

    int points;

    @CreationTimestamp
    Instant createdAt;
}
