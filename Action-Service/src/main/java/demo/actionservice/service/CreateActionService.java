package demo.actionservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.actionservice.client.UserClient;
import demo.actionservice.client.dto.AddPointsRequest;
import demo.actionservice.dto.ActionResponse;
import demo.actionservice.dto.CreateActionRequest;
import demo.actionservice.entity.ActionEntity;
import demo.actionservice.event.ActionCreatedEvent;
import demo.actionservice.kafka.outbox.ActionOutboxEntity;
import demo.actionservice.kafka.outbox.ActionOutboxRepository;
import demo.actionservice.mapper.ActionMapper;
import demo.actionservice.repository.ActionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateActionService {

    private final ActionRepository repository;
    private final ActionMapper mapper;
    private final UserClient client;
    private final ObjectMapper objectMapper;
    private final ActionOutboxRepository outboxRepository;

    public CreateActionService(ActionRepository repository, ActionMapper mapper, UserClient client, ObjectMapper objectMapper, ActionOutboxRepository outboxRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.client = client;
        this.objectMapper = objectMapper;
        this.outboxRepository = outboxRepository;
    }

    @Transactional  //Eğer outbox kaydedilemezse Action da rollback olur
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

        try {
            // 3) Event'i JSON'a çevir
            String json = objectMapper.writeValueAsString(event);

            // 4) Outbox'a kaydet
            ActionOutboxEntity outbox = new ActionOutboxEntity(
                    entity.getActionId(),
                    "ActionCreatedEvent",
                    json
            );

            outboxRepository.save(outbox);

        } catch (Exception e) {
            // JSON hatası olursa rollback
            throw new IllegalStateException("Outbox kaydedilirken hata oluştu", e);
        }

        return mapper.toResponse(entity);

    }
}
