package demo.actionservice.repository;

import demo.actionservice.entity.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ActionRepository extends JpaRepository<ActionEntity, UUID> {
    List<ActionEntity> findByUserId(UUID userId);

}
