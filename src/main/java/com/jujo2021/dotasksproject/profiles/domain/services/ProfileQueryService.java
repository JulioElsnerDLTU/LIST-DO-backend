package com.jujo2021.dotasksproject.profiles.domain.services;
import com.jujo2021.dotasksproject.profiles.domain.model.aggregates.Profile;
import com.jujo2021.dotasksproject.profiles.domain.model.queries.GetAllProfilesQuery;
import com.jujo2021.dotasksproject.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.jujo2021.dotasksproject.profiles.domain.model.queries.GetProfileByIdQuery;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    Optional<Profile> handle(GetProfileByEmailQuery query);
    List<Profile> handle (GetAllProfilesQuery query);
}
