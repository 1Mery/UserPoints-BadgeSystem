package demo.userservice.controller;

import demo.userservice.dto.AddPointsRequest;
import demo.userservice.dto.CreateUserRequest;
import demo.userservice.dto.UserResponse;
import demo.userservice.service.AddPointsService;
import demo.userservice.service.CreateUserService;
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

    public UserController(CreateUserService createUserService,
                          GetUserByIdService getUserByIdService,
                          AddPointsService addPointsService) {
        this.createUserService = createUserService;
        this.getUserByIdService = getUserByIdService;
        this.addPointsService = addPointsService;
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
}
