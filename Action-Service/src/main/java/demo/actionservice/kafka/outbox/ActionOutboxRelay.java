package demo.actionservice.kafka.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.actionservice.event.ActionCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActionOutboxRelay {

    private final ActionOutboxRepository repository;
    private final ObjectMapper objectMapper;
    private final StreamBridge streamBridge;

    // her 10 saniyede bir pending eventleri oku
    @Scheduled(fixedDelay = 10_000)
    @Transactional
    public void relay() {
        List<ActionOutboxEntity> pending =
                repository.findTop50ByStatusOrderByCreatedAtAsc(OutboxStatus.NEW);

        for (ActionOutboxEntity entity : pending) {
            try {
                // payload'u tekrar event objesine çevir
                ActionCreatedEvent event = objectMapper.readValue(
                        entity.getPayload(),
                        ActionCreatedEvent.class
                );

                // Kafka'ya publish et
                streamBridge.send("action-events-out-0", event);

                // başarılı ise status SENT
                entity.setStatus(OutboxStatus.SENT);
                entity.setLastAttemptAt(Instant.now());
                repository.save(entity);

            } catch (Exception e) {
                log.error("Outbox relay failed for id {}", entity.getId(), e);
                // hata aldıysak FAILED olarak işaretleyelim (veya deneme sayısı vs. eklenir)
                entity.setStatus(OutboxStatus.FAILED);
                entity.setLastAttemptAt(Instant.now());
                repository.save(entity);
            }
        }
    }
}
