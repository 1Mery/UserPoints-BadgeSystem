package demo.actionservice.dto;

import demo.actionservice.entity.ActionType;

import java.util.UUID;

public record CreateActionRequest(UUID userId,
                                  ActionType actionType) {
}
