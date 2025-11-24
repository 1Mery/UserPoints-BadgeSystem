package demo.actionservice.service;

import demo.actionservice.dto.ActionResponse;
import demo.actionservice.entity.ActionEntity;
import demo.actionservice.mapper.ActionMapper;
import demo.actionservice.repository.ActionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetActionsByUserIdService {

    private final ActionRepository repository;
    private final ActionMapper mapper;

    public GetActionsByUserIdService(ActionRepository repository, ActionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ActionResponse> getByUserId(UUID userId){
        if (userId == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }


        List<ActionEntity> actions = repository.findByUserId(userId);

        return mapper.toResponseList(actions);
    }
}
