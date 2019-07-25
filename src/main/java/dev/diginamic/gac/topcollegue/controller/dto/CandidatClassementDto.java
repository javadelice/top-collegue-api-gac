package dev.diginamic.gac.topcollegue.controller.dto;

import java.util.Objects;

public class CandidatClassementDto {
    private String pictureurl;
    private String lastName;
    private String firstName;
    private Integer score;

    public CandidatClassementDto() {
    }

    public CandidatClassementDto(String pictureurl, String lastName, String firstName, Integer score) {
        this.pictureurl = pictureurl;
        this.lastName = lastName;
        this.firstName = firstName;
        this.score = score;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
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
        sb.append("pictureurl='").append(pictureurl).append('\'');
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
        return Objects.equals(pictureurl, that.pictureurl) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pictureurl, lastName, firstName, score);
    }
}
