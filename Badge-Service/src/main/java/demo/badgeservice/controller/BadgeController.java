package demo.badgeservice.controller;

import demo.badgeservice.dto.BadgeResponse;
import demo.badgeservice.service.GetBadgesByUserIdService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/badges")
public class BadgeController {

    private final GetBadgesByUserIdService getBadgesByUserIdService;

    public BadgeController(
                           GetBadgesByUserIdService getBadgesByUserIdService) {

        this.getBadgesByUserIdService = getBadgesByUserIdService;
    }

    //rozet listesi dönüyor
    @GetMapping("/user/{userId}")
    public List<BadgeResponse> getByUser(@PathVariable UUID userId) {
        return getBadgesByUserIdService.getByUserId(userId);
    }
}