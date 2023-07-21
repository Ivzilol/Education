package softuni.exam.instagraphlite.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.transform.sax.SAXResult;

public class UserSeedDto {

    @Expose
    @NotNull
    @Size(min = 2, max = 18)
    private String username;

    @Expose
    @NotNull
    @Size(min = 4)
    private String password;

    @Expose
    @NotNull
    private String profilePicture;

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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
