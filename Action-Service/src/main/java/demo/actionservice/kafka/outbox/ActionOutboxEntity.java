package demo.actionservice.kafka.outbox;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "action_outbox")
public class ActionOutboxEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "action_id", nullable = false)
    private UUID actionId;   // hangi action'a ait bu event

    @Column(name = "event_type", nullable = false)
    private String eventType;  // "ActionCreatedEvent" gibi

    @Lob
    @Column(name = "payload", nullable = false)
    private String payload;    // Event'in JSON hali

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OutboxStatus status = OutboxStatus.NEW;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "last_attempt_at")
    private Instant lastAttemptAt;

    public ActionOutboxEntity(UUID actionId, String eventType, String payload) {
        this.actionId = actionId;
        this.eventType = eventType;
        this.payload = payload;
        this.status = OutboxStatus.NEW;
        this.createdAt = Instant.now();
    }

}