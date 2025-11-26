package demo.actionservice.event;

import java.util.UUID;

public record ActionCreatedEvent(
        UUID userId,
        String actionType,
        int points
) {}

