package demo.badgeservice.dto;

import demo.badgeservice.entity.BadgeType;

import java.time.Instant;
import java.util.UUID;

public record BadgeResponse(
        UUID id,
        UUID userId,
        BadgeType badgeType,
        Instant createdAt
) {
}
