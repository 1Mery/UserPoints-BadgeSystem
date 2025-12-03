package demo.userservice.service;

import demo.userservice.client.BadgeClient;
import demo.userservice.dto.BadgeResponse;
import demo.userservice.dto.UserResponse;
import demo.userservice.entity.UserEntity;
import demo.userservice.mapper.UserMapper;
import demo.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetUserByIdService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final BadgeClient badgeClient;

    public GetUserByIdService(UserRepository repository, UserMapper mapper, BadgeClient badgeClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.badgeClient = badgeClient;
    }


    public UserResponse getById(UUID id) {
        UserEntity entity = repository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found"));

        // Badge-Service’ten rozetleri al
        List<BadgeResponse> badges = badgeClient.getBadgesByUserId(id);

        BadgeResponse badge = null;
        if (!badges.isEmpty()) {
            badge = badges.get(0);
        }// tek rozet

        return mapper.toResponse(entity, badge);
    }
}
