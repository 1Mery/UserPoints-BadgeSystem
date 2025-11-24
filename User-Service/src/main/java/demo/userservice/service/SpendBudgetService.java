package demo.userservice.service;

import demo.userservice.dto.SpendBudgetRequest;
import demo.userservice.dto.UserResponse;
import demo.userservice.entity.UserEntity;
import demo.userservice.mapper.UserMapper;
import demo.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class SpendBudgetService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public SpendBudgetService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserResponse spendBudget(UUID userId, SpendBudgetRequest request) {

        UserEntity user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //mevcut bütçeyi al
        BigDecimal currentBudget = user.getTotalBudget();

        //harcanacak tutarı BigDecimal'e çevir
        BigDecimal amountToSpend = BigDecimal.valueOf(request.amount());

        //bütçe yeterli mi
        if (currentBudget.compareTo(amountToSpend) < 0) {
            throw new RuntimeException("Insufficient budget");
        }

        //yeni bütçeyi hesapla
        BigDecimal newBudget = currentBudget.subtract(amountToSpend);

        user.setTotalBudget(newBudget);

        UserEntity saved = repository.save(user);

        return mapper.toResponse(saved);
    }
}
