package demo.userservice.dto;

import java.util.UUID;

public record UserResponse(UUID id,
                           String userName,
                           String email) {
}
