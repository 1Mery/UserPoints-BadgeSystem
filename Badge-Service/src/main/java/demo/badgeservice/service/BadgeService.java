package demo.badgeservice.service;

import demo.badgeservice.entity.BadgeEntity;
import demo.badgeservice.entity.BadgeType;
import demo.badgeservice.repository.BadgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BadgeService {

    private final BadgeRepository badgeRepository;

    public BadgeService(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    public void updateUserBadge(UUID userId, int totalPoints) {

        // enum içindeki mantık
        BadgeType newBadge = BadgeType.fromPoints(totalPoints);

        List<BadgeEntity> oldBadges = badgeRepository.findByUserId(userId);

        for (BadgeEntity b : oldBadges) {
            if (b.getBadgeType() == newBadge) {
                return; // zaten aynı rozet varsa çık
            }
        }

        badgeRepository.deleteAll(oldBadges);

        BadgeEntity badge = new BadgeEntity();
        badge.setUserId(userId);
        badge.setBadgeType(newBadge);

        badgeRepository.save(badge);
    }
}