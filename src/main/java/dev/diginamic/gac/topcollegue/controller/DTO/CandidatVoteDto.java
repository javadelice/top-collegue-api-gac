package dev.diginamic.gac.topcollegue.controller.dto;

import java.util.Objects;

public class CandidatVoteDto {
    private String id;
    private String lastName;
    private String firstName;
    private String PictureUrl;
    private Boolean score;

    public CandidatVoteDto() {
    }

    public CandidatVoteDto(String id, String lastName, String firstName, String pictureUrl, Boolean score) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        PictureUrl = pictureUrl;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPictureUrl() {
        return PictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        PictureUrl = pictureUrl;
    }

    public Boolean getScore() {
        return score;
    }

    public void setScore(Boolean score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidatVoteDto that = (CandidatVoteDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(PictureUrl, that.PictureUrl) &&
                Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, PictureUrl, score);
    }
}
