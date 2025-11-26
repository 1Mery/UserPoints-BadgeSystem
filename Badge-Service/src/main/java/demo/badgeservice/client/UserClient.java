package demo.badgeservice.client;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;


@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserClient {

    @GetMapping("/api/v1/users/{userId}/total-points")
    Integer getTotalPoints(@PathVariable("userId") UUID userId);
}
