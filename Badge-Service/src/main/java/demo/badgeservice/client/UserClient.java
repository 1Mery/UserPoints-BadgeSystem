package demo.badgeservice.client;

import demo.badgeservice.dto.BadgeNotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;


@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserClient {

    @GetMapping("/api/v1/users/{userId}/total-points")
    Integer getTotalPoints(@PathVariable("userId") UUID userId);

    @PostMapping("/api/v1/users/{userId}/badge-updated")
    void badgeUpdated(@PathVariable("userId") UUID userId, BadgeNotificationRequest request);
}
