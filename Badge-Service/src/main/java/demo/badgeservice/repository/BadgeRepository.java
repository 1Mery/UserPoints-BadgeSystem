package demo.badgeservice.repository;

import demo.badgeservice.entity.BadgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BadgeRepository extends JpaRepository<BadgeEntity, UUID> {

    List<BadgeEntity> findByUserId(UUID userId);
}
