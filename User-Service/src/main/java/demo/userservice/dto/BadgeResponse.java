package demo.userservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record BadgeResponse(
        UUID id,
        String badgeType,
        LocalDateTime createdAt
) {}
