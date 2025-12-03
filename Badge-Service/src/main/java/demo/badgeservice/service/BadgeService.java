package demo.badgeservice.service;

import demo.badgeservice.client.UserClient;
import demo.badgeservice.dto.BadgeNotificationRequest;
import demo.badgeservice.entity.BadgeEntity;
import demo.badgeservice.entity.BadgeType;
import demo.badgeservice.repository.BadgeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final UserClient client;

    public BadgeService(BadgeRepository badgeRepository, UserClient client) {
        this.badgeRepository = badgeRepository;
        this.client = client;
    }

    @Transactional
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

        // notify user
        try {
            BadgeNotificationRequest req = new BadgeNotificationRequest(newBadge, "Conguralations! Win a new badge.");
            client.badgeUpdated(userId, req);
        } catch (Exception e) {
            log.error("Failed to notify user {} about badge change", userId, e);
            // hata loglandı: notify başarısızsa rozet DB zaten güncellendiği için overall akış bozulmaz
        }
    }
}