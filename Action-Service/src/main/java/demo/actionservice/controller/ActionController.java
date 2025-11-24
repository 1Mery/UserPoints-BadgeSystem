package demo.actionservice.controller;

import demo.actionservice.dto.ActionResponse;
import demo.actionservice.dto.CreateActionRequest;
import demo.actionservice.service.CreateActionService;
import demo.actionservice.service.GetActionsByUserIdService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/actions")
public class ActionController {

    private final CreateActionService service;
    private final GetActionsByUserIdService getService;

    public ActionController(CreateActionService service, GetActionsByUserIdService getService) {
        this.service = service;
        this.getService = getService;
    }

    @PostMapping
    public ActionResponse createAction(@RequestBody CreateActionRequest request){
        return service.createAction(request);
    }

    @GetMapping("/user/{userId}")
    public List<ActionResponse> getByUserId(@PathVariable UUID userId){
        return getService.getByUserId(userId);
    }
}
