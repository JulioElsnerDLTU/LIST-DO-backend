package com.jujo2021.dotasksproject.profiles.domain.model.aggregates;
import com.jujo2021.dotasksproject.profiles.domain.model.commands.CreateProfileCommand;
import com.jujo2021.dotasksproject.profiles.domain.model.commands.UpdateProfileCommand;
import com.jujo2021.dotasksproject.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(unique = true)
    @Getter
    private String username;

    @Column
    @Getter
    private String email;

    @Column
    @Getter
    private String imageUrl;

    @Column
    @Getter
    private String biography;

    @Column
    @Getter
    private String password;

    public Profile() {
    }

    public Profile(String username, String email, String imageUrl, String biography, String password) {
        this.username = username;
        this.email = email;
        this.imageUrl = imageUrl;
        this.biography = biography;
        this.password = password;
    }

    public Profile(CreateProfileCommand command) {
        this.username = command.username();
        this.email = command.email();
        this.imageUrl = command.imageUrl();
        this.biography = command.biography();
        this.password = command.password();
    }

    public Profile updateProfile(UpdateProfileCommand command) {
        this.getId();
        this.biography = command.biography();
        this.imageUrl = command.imageUrl();
        return this;
    }

    public Profile updateProfilePic(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBiography() {
        return biography;
    }

    public String getPassword() {
        return password;
    }
}
