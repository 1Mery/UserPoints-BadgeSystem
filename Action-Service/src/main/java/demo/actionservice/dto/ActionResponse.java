package demo.actionservice.dto;

import demo.actionservice.entity.ActionType;


import java.time.Instant;
import java.util.UUID;

public record ActionResponse(
        UUID actionId,
        UUID userId,
        ActionType actionType,
        int points,
        Instant createdAt
) {
}
