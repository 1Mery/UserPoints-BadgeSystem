package demo.actionservice.kafka.outbox;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ActionOutboxRepository extends JpaRepository<ActionOutboxEntity, UUID> {

    // En eski 50 NEW kaydı getir
    List<ActionOutboxEntity> findTop50ByStatusOrderByCreatedAtAsc(OutboxStatus status);
}
