package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;

public class PictureUrlDto {

    @Expose
    @Size(max = 20)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
