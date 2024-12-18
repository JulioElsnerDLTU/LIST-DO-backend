package com.jujo2021.dotasksproject.profiles.interfaces.rest.transform;

import com.jujo2021.dotasksproject.profiles.domain.model.aggregates.Profile;
import com.jujo2021.dotasksproject.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(entity.getId(), entity.getEmail(), entity.getUsername(), entity.getImageUrl(), entity.getPassword(), entity.getBiography());
    }
}
