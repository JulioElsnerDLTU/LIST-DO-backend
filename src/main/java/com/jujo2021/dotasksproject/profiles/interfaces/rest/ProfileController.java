package com.jujo2021.dotasksproject.profiles.interfaces.rest;

import com.jujo2021.dotasksproject.profiles.domain.model.commands.DeleteProfileCommand;
import com.jujo2021.dotasksproject.profiles.domain.model.commands.UpdateProfileCommand;
import com.jujo2021.dotasksproject.profiles.domain.model.queries.GetAllProfilesQuery;
import com.jujo2021.dotasksproject.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.jujo2021.dotasksproject.profiles.domain.model.queries.GetProfileByIdQuery;
import com.jujo2021.dotasksproject.profiles.domain.services.ProfileCommandService;
import com.jujo2021.dotasksproject.profiles.domain.services.ProfileQueryService;
import com.jujo2021.dotasksproject.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.jujo2021.dotasksproject.profiles.interfaces.rest.resources.CreateProfileResource;
import com.jujo2021.dotasksproject.profiles.interfaces.rest.resources.ProfileResource;
import com.jujo2021.dotasksproject.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.jujo2021.dotasksproject.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Users Management Endpoints")
public class ProfileController {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    /**
     * Create a new user
     * @param createUserResource
     * @return
     */
    @PostMapping
    public ResponseEntity<ProfileResource> createUser(@RequestBody CreateProfileResource createUserResource) {
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(createUserResource);
        var userId = profileCommandService.handle(createProfileCommand);
        var getUserByIdQuery = new GetProfileByIdQuery(userId);
        var user = profileQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        var userResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    /**
     * Get user by id
     * @param profileId
     * @return
     */
    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getUserById(@PathVariable Long profileId) {
        var getUserByIdQuery = new GetProfileByIdQuery(profileId);
        var user = profileQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        var userResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }
    /**
     * Get user by email
     * @param userEmail
     * @return
     */
    @GetMapping("/email/{userEmail}")
    public ResponseEntity<ProfileResource> getuserByEmail(@PathVariable String userEmail) {
        var getUserByIdQuery = new GetProfileByEmailQuery(userEmail);
        var user = profileQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        var UserResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(UserResource);
    }
    /**
     * Get all users
     * @param
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllusers() {
        var getAllusersQuery = new GetAllProfilesQuery();
        var users = profileQueryService.handle(getAllusersQuery);
        var UserResources = users.stream().map(ProfileResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(UserResources);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ProfileResource> updateuser(@PathVariable Long userId, @RequestBody CreateProfileResource createUserResource) {
        var updateUserCommand = new UpdateProfileCommand(userId, createUserResource.imageUrl(), createUserResource.biography());
        profileCommandService.handle(updateUserCommand);
        var getUserByIdQuery = new GetProfileByIdQuery(userId);
        var user = profileQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        var userResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    /**
     * Deletes an user.
     *
     * @param userId
     * @return Deletion confirmation message
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteuser(@PathVariable Long userId) {
        var deleteuserCommand = new DeleteProfileCommand(userId);
        profileCommandService.handle(deleteuserCommand);
        return ResponseEntity.ok("User with given id successfully deleted");
    }
}