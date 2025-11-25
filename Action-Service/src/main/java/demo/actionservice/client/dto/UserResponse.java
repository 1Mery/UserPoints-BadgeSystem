package demo.actionservice.client.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String userName,
        String email
) {
}
