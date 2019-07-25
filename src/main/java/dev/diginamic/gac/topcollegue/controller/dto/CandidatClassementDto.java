package dev.diginamic.gac.topcollegue.controller.dto;

import java.util.Objects;

public class CandidatClassementDto {
    private String pictureUrl;
    private String lastName;
    private String firstName;
    private Integer score;

    public CandidatClassementDto() {
    }

    public CandidatClassementDto(String pictureUrl, String lastName, String firstName, Integer score) {
        this.pictureUrl = pictureUrl;
        this.lastName = lastName;
        this.firstName = firstName;
        this.score = score;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CandidatClassement{");
        sb.append("pictureurl='").append(pictureUrl).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidatClassementDto that = (CandidatClassementDto) o;
        return Objects.equals(pictureUrl, that.pictureUrl) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pictureUrl, lastName, firstName, score);
    }
}
