package demo.userservice.service;

import demo.userservice.entity.UserEntity;
import demo.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetTotalPointsService {

    private final UserRepository userRepository;

    public GetTotalPointsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer getTotalPoints(UUID userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getTotalPoints();
    }
}
