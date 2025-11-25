package demo.badgeservice.service;

import demo.badgeservice.dto.BadgeResponse;
import demo.badgeservice.dto.CreateBadgeRequest;
import demo.badgeservice.entity.BadgeEntity;
import demo.badgeservice.mapper.BadgeMapper;
import demo.badgeservice.repository.BadgeRepository;
import org.springframework.stereotype.Service;


@Service
public class CreateBadgeService {

    private final BadgeRepository repository;
    private final BadgeMapper mapper;

    public CreateBadgeService(BadgeRepository repository, BadgeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public BadgeResponse createBadge(CreateBadgeRequest request){
        if (request.userId()==null){
            throw new IllegalArgumentException("USerId cannot be null");
        }

        if (request.badgeType()==null){
            throw new IllegalArgumentException("Badgetype cannot be null");
        }

        BadgeEntity entity=new BadgeEntity();
        entity.setUserId(request.userId());
        entity.setBadgeType(request.badgeType());

        BadgeEntity saved=repository.save(entity);

        return mapper.toResponse(saved);
    }
}
