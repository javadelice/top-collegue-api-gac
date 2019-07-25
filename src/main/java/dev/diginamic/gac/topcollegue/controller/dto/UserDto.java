package dev.diginamic.gac.topcollegue.controller.dto;


public class UserDto {
    private String username;
    private String lastName;
    private String firstName;
    private String matricule;
    private String pictureUrl;

    public UserDto() {
    }

    public UserDto(String username, String lastName, String firstName, String matricule, String pictureUrl) {
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.matricule = matricule;
        this.pictureUrl = pictureUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDto{");
        sb.append("username='").append(username).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", matricule='").append(matricule).append('\'');
        sb.append(", pictureUrl='").append(pictureUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
