package com.jujo2021.dotasksproject.profiles.application.commandservices;
import com.jujo2021.dotasksproject.profiles.domain.model.aggregates.Profile;
import com.jujo2021.dotasksproject.profiles.domain.model.commands.CreateProfileCommand;
import com.jujo2021.dotasksproject.profiles.domain.model.commands.DeleteProfileCommand;
import com.jujo2021.dotasksproject.profiles.domain.model.commands.UpdateProfileCommand;
import com.jujo2021.dotasksproject.profiles.domain.services.ProfileCommandService;
import com.jujo2021.dotasksproject.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Long handle(CreateProfileCommand command) {
        if (profileRepository.existsByEmail(command.email())){
            throw new IllegalArgumentException("Profile with same email already exists");
        }
        if(profileRepository.existsByUsername(command.username())) {
            throw new IllegalArgumentException("Profile with same username already exists");
        }
        var profile = new Profile(command);
        try {
            profileRepository.save(profile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving profile: " + e.getMessage());
        }
        return profile.getId();
    }

    @Override
    public void handle(DeleteProfileCommand command) {
        if (!profileRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Profile does not exist");
        }
        try {
            profileRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting profile: " + e.getMessage());
        }

    }

    @Override
    public Optional<Profile> handle(UpdateProfileCommand command) {
        var result = profileRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Profile does not exist");
        var profileToUpdate = result.get();
        try {
            var updatedProfile = profileRepository.save(profileToUpdate.updateProfile(command));
            return Optional.of(updatedProfile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating profile: " + e.getMessage());
        }
    }
}
