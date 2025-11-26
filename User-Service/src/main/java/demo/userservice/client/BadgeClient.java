package demo.userservice.client;

import demo.userservice.dto.BadgeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Component
@FeignClient(name = "badge-service", url = "http://localhost:8082/api/v1/badges")
public interface BadgeClient {

    @GetMapping("/user/{userId}")
    List<BadgeResponse> getBadgesByUserId(@PathVariable("userId") UUID userId);
}

