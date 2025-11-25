package demo.badgeservice.controller;

import demo.badgeservice.dto.BadgeResponse;
import demo.badgeservice.dto.CreateBadgeRequest;
import demo.badgeservice.service.CreateBadgeService;
import demo.badgeservice.service.GetBadgesByUserIdService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/badges")
public class BadgeController {

    private final CreateBadgeService createBadgeService;
    private final GetBadgesByUserIdService getBadgesByUserIdService;

    public BadgeController(CreateBadgeService createBadgeService,
                           GetBadgesByUserIdService getBadgesByUserIdService) {
        this.createBadgeService = createBadgeService;
        this.getBadgesByUserIdService = getBadgesByUserIdService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BadgeResponse create(@RequestBody CreateBadgeRequest request) {
        return createBadgeService.createBadge(request);
    }

    @GetMapping("/user/{userId}")
    public List<BadgeResponse> getByUser(@PathVariable UUID userId) {
        return getBadgesByUserIdService.getByUserId(userId);
    }
}