package demo.actionservice.service;

import demo.actionservice.dto.ActionResponse;
import demo.actionservice.dto.CreateActionRequest;
import demo.actionservice.entity.ActionEntity;
import demo.actionservice.entity.ActionType;
import demo.actionservice.mapper.ActionMapper;
import demo.actionservice.repository.ActionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateActionService {

    private final ActionRepository repository;
    private final ActionMapper mapper;

    public CreateActionService(ActionRepository repository, ActionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ActionResponse createAction(CreateActionRequest request){
        if (request.userId() == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (request.actionType() == null) {
            throw new IllegalArgumentException("ActionType cannot be null");
        }

        int points = request.actionType().getPoints();

        ActionEntity entity=new ActionEntity();

        entity.setUserId(request.userId());
        entity.setActionType(request.actionType());
        entity.setPoints(points);

        repository.save(entity);

        return mapper.toResponse(entity);

    }
}
