package demo.actionservice.mapper;

import demo.actionservice.dto.ActionResponse;
import demo.actionservice.entity.ActionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActionMapper {
    public ActionResponse toResponse(ActionEntity entity){
        return new ActionResponse(
                entity.getActionId(),
                entity.getUserId(),
                entity.getActionType(),
                entity.getPoints(),
                entity.getCreatedAt()

        );
    }

    public List<ActionResponse> toResponseList(List<ActionEntity> entities){
        List<ActionResponse> entities1=new ArrayList<>();

        for (ActionEntity e : entities) {
            ActionResponse dto = toResponse(e);
            entities1.add(dto);
        }
        return entities1;
    }

}
