package demo.actionservice.service;

import demo.actionservice.client.UserClient;
import demo.actionservice.client.dto.AddPointsRequest;
import demo.actionservice.dto.ActionResponse;
import demo.actionservice.dto.CreateActionRequest;
import demo.actionservice.entity.ActionEntity;
import demo.actionservice.event.ActionCreatedEvent;
import demo.actionservice.event.ActionEventPublisher;
import demo.actionservice.mapper.ActionMapper;
import demo.actionservice.repository.ActionRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateActionService {

    private final ActionRepository repository;
    private final ActionMapper mapper;
    private final UserClient client;
    private final ActionEventPublisher publisher;

    public CreateActionService(ActionRepository repository, ActionMapper mapper, UserClient client, ActionEventPublisher publisher) {
        this.repository = repository;
        this.mapper = mapper;
        this.client = client;
        this.publisher = publisher;
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

        // Feign ile User-Service puan ekliyoruz
        AddPointsRequest addPointsRequest= new AddPointsRequest(points);
        client.addPoints(request.userId(),addPointsRequest);

        //Kafka event gönderme
        ActionCreatedEvent event = new ActionCreatedEvent(
                entity.getUserId(),
                entity.getActionType().name(),
                entity.getPoints()
        );
        publisher.publish(event);

        return mapper.toResponse(entity);

    }
}
