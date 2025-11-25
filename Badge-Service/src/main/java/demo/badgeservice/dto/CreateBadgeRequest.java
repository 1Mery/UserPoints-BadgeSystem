package demo.badgeservice.dto;

import demo.badgeservice.entity.BadgeType;

import java.util.UUID;

public record CreateBadgeRequest(
        UUID userId,
        BadgeType badgeType
) {
}
