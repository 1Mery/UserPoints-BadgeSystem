package demo.actionservice.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActionEventPublisher {

    private final StreamBridge streamBridge;

    public void publish(ActionCreatedEvent event) {
        streamBridge.send("actionEvents-out-0", event);
        log.info("Event sent to Kafka: " + event);
    }
}

