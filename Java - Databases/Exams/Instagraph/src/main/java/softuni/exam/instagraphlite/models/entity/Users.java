package softuni.exam.instagraphlite.models.entity;

import softuni.exam.instagraphlite.models.entity.Pictures;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Pictures profilePicture;

    public Users() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Pictures getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Pictures profilePicture) {
        this.profilePicture = profilePicture;
    }
}
