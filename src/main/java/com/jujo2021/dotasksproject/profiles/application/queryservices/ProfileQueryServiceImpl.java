package com.jujo2021.dotasksproject.profiles.application.queryservices;
import com.jujo2021.dotasksproject.profiles.domain.model.aggregates.Profile;
import com.jujo2021.dotasksproject.profiles.domain.model.queries.GetAllProfilesQuery;
import com.jujo2021.dotasksproject.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.jujo2021.dotasksproject.profiles.domain.model.queries.GetProfileByIdQuery;
import com.jujo2021.dotasksproject.profiles.domain.services.ProfileQueryService;
import com.jujo2021.dotasksproject.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.id());
    }

    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        return profileRepository.findByEmail(query.email());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }
}
