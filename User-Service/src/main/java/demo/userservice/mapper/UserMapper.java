package demo.userservice.mapper;

import demo.userservice.dto.UserResponse;
import demo.userservice.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(UserEntity entity){
        return new UserResponse(
                entity.getUserId(),
                entity.getUserName(),
                entity.getEmail()
        );
    }
}
