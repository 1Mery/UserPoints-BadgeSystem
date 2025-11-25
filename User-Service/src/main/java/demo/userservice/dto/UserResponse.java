package demo.userservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record UserResponse(UUID id,
                           String userName,
                           String email,
                           int totalPoints,
                           BigDecimal totalBudget) {
}
