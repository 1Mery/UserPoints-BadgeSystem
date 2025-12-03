package demo.userservice.mapper;

import demo.userservice.dto.BadgeResponse;
import demo.userservice.dto.UserResponse;
import demo.userservice.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(UserEntity entity){
        return new UserResponse(
                entity.getUserId(),
                entity.getUserName(),
                entity.getEmail(),
                entity.getTotalPoints(),
                entity.getTotalBudget(),
                null   // yeni eklenen badge alanı, create user'da henüz rozeti yok
        );
    }


    public UserResponse toResponse(UserEntity entity, BadgeResponse badge){
        return new UserResponse(
                entity.getUserId(),
                entity.getUserName(),
                entity.getEmail(),
                entity.getTotalPoints(),
                entity.getTotalBudget(),
                badge
        );
    }
}
