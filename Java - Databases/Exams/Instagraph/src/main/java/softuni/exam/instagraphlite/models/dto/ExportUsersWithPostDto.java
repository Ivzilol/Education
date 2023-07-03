package softuni.exam.instagraphlite.models.dto;


public class ExportUsersWithPostDto {

    private String caption;

    private Long userId;

    private String username;

    private Double size;

    public ExportUsersWithPostDto(String caption, Long userId, String username, Double size) {
        this.caption = caption;
        this.userId = userId;
        this.username = username;
        this.size = size;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
