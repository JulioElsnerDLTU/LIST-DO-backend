package com.jujo2021.dotasksproject.profiles.domain.services;

import com.jujo2021.dotasksproject.profiles.domain.model.aggregates.Profile;
import com.jujo2021.dotasksproject.profiles.domain.model.commands.CreateProfileCommand;
import com.jujo2021.dotasksproject.profiles.domain.model.commands.DeleteProfileCommand;
import com.jujo2021.dotasksproject.profiles.domain.model.commands.UpdateProfileCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProfileCommandService {
    Long handle(CreateProfileCommand command);
    void handle(DeleteProfileCommand command);
    Optional<Profile> handle(UpdateProfileCommand command);
}
