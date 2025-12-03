package demo.userservice.service;

import demo.userservice.dto.AddPointsRequest;
import demo.userservice.dto.UserResponse;
import demo.userservice.entity.UserEntity;
import demo.userservice.event.ActionCreatedEvent;
import demo.userservice.mapper.UserMapper;
import demo.userservice.repository.UserRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddPointsService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final StreamBridge streamBridge;

    public AddPointsService(UserRepository repository, UserMapper mapper, StreamBridge streamBridge) {
        this.repository = repository;
        this.mapper = mapper;
        this.streamBridge = streamBridge;
    }

    public UserResponse addPoints(UUID userId, AddPointsRequest request) {

        // user'ı bul
        UserEntity user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // mevcut puanı al
        int currentPoints = user.getTotalPoints();

        // yeni puan  mevcut + gelen
        int newTotal = currentPoints + request.points();

        // entity'yi güncelle
        user.setTotalPoints(newTotal);

        streamBridge.send(
                "actionEvents-out-0",
                new ActionCreatedEvent(
                        userId,
                        "ADD_POINTS",
                        request.points(),
                        newTotal
                )
        );


        UserEntity saved = repository.save(user);

        return mapper.toResponse(saved);
    }
}
