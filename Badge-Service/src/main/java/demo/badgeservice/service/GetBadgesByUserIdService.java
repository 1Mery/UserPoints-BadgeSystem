package demo.badgeservice.service;

import demo.badgeservice.dto.BadgeResponse;
import demo.badgeservice.mapper.BadgeMapper;
import demo.badgeservice.repository.BadgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetBadgesByUserIdService {

    private final BadgeRepository repository;
    private final BadgeMapper mapper;

    public GetBadgesByUserIdService(BadgeRepository repository, BadgeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<BadgeResponse> getByUserId(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }

        return mapper.toResponseList(repository.findByUserId(userId));
    }
}
