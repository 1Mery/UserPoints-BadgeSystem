package demo.userservice.service;

import demo.userservice.dto.UserResponse;
import demo.userservice.entity.UserEntity;
import demo.userservice.mapper.UserMapper;
import demo.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetUserByIdService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public GetUserByIdService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserResponse getById(UUID id){
        UserEntity entity=repository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found"));

        return mapper.toResponse(entity);
    }
}
