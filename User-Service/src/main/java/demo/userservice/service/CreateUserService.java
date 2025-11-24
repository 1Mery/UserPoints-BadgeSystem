package demo.userservice.service;

import demo.userservice.dto.CreateUserRequest;
import demo.userservice.dto.UserResponse;
import demo.userservice.entity.UserEntity;
import demo.userservice.mapper.UserMapper;
import demo.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public CreateUserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserResponse createUser(CreateUserRequest request){
        if (request.userName()==null|| request.userName().isBlank()){
            throw new IllegalArgumentException("Name cannot be emty");
        }

        if (request.email()==null|| request.email().isBlank()){
            throw new IllegalArgumentException("Email cannot be emty");
        }

        UserEntity user=new UserEntity();

        user.setUserName(request.userName());
        user.setEmail(request.email());

        UserEntity saved = repository.save(user);

        return mapper.toResponse(saved);
    }
}
