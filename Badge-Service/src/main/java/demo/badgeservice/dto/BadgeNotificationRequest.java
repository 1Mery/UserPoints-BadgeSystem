package demo.badgeservice.dto;

import demo.badgeservice.entity.BadgeType;

public record BadgeNotificationRequest(
        BadgeType badgeType,
        String message
) {
}
