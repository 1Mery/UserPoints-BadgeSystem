package demo.badgeservice.event;

import demo.badgeservice.client.UserClient;
import demo.badgeservice.service.BadgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ActionEventConsumer {

    private final UserClient userClient;
    private final BadgeService badgeService;

    @Bean
    public Consumer<ActionCreatedEvent> actionCreatedConsumer() {
        return event -> {
            log.info("Badge-Service received event: {}", event);

            // Kullanıcının güncel toplam puanını user-service'ten çek
            Integer totalPoints = userClient.getTotalPoints(event.userId());
            log.info("Total points for user {} = {}", event.userId(), totalPoints);

            // Badge'i güncelle (içerde hangi badge olacağını hesaplıyor)
            badgeService.updateUserBadge(event.userId(), totalPoints);
        };
    }
}

