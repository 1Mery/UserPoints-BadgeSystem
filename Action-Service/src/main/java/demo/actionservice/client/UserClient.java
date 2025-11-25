package demo.actionservice.client;

import demo.actionservice.client.dto.AddPointsRequest;
import demo.actionservice.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(
        name = "user-service",
        url = "http://localhost:8080"
)
public interface UserClient {

    @PostMapping("/api/v1/users/{id}/add-points")
    UserResponse addPoints(
            @PathVariable("id") UUID userId,
            @RequestBody AddPointsRequest request
    );
}
