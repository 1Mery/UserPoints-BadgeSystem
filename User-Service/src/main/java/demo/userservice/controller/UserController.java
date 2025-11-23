package demo.userservice.controller;

import demo.userservice.dto.CreateUserRequest;
import demo.userservice.dto.UserResponse;
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

    public UserController(CreateUserService createUserService, GetUserByIdService getUserByIdService) {
        this.createUserService = createUserService;
        this.getUserByIdService = getUserByIdService;
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

}
