package demo.badgeservice.mapper;

import demo.badgeservice.dto.BadgeResponse;
import demo.badgeservice.entity.BadgeEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BadgeMapper {

    public BadgeResponse toResponse(BadgeEntity entity) {
        return new BadgeResponse(
                entity.getId(),
                entity.getUserId(),
                entity.getBadgeType(),
                entity.getCreatedAt()
        );
    }

    public List<BadgeResponse> toResponseList(List<BadgeEntity> entities) {
        return entities.stream()
                .map(this::toResponse)
                .toList();
    }
}
