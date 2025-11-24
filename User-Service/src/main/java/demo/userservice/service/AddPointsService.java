package demo.userservice.service;

import demo.userservice.dto.AddPointsRequest;
import demo.userservice.dto.UserResponse;
import demo.userservice.entity.UserEntity;
import demo.userservice.mapper.UserMapper;
import demo.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddPointsService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public AddPointsService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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

        UserEntity saved = repository.save(user);

        return mapper.toResponse(saved);
    }
}
