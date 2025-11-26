package demo.userservice.controller;

import demo.userservice.dto.AddPointsRequest;
import demo.userservice.dto.CreateUserRequest;
import demo.userservice.dto.UserResponse;
import demo.userservice.service.AddPointsService;
import demo.userservice.service.CreateUserService;
import demo.userservice.service.GetTotalPointsService;
import demo.userservice.service.GetUserByIdService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final CreateUserService createUserService;
    private final GetUserByIdService getUserByIdService;
    private final AddPointsService addPointsService;
    private final GetTotalPointsService getTotalPointsService;

    public UserController(CreateUserService createUserService, GetUserByIdService getUserByIdService, AddPointsService addPointsService, GetTotalPointsService getTotalPointsService) {
        this.createUserService = createUserService;
        this.getUserByIdService = getUserByIdService;
        this.addPointsService = addPointsService;
        this.getTotalPointsService = getTotalPointsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody CreateUserRequest request){
        return createUserService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable UUID id){
        return getUserByIdService.getById(id);
    }

    @PostMapping("/{id}/add-points")
    public UserResponse addPoints(@PathVariable UUID id,
                                  @RequestBody AddPointsRequest request) {
        return addPointsService.addPoints(id, request);
    }

    @GetMapping("{userId}/total-points")
    public Integer getTotalPoints(@PathVariable UUID userId) {
        return getTotalPointsService.getTotalPoints(userId);
    }

}
