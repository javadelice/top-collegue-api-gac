package dev.diginamic.gac.topcollegue.controller.DTO;

public class VoteDTO {

    private String idJudge;
    private String idCandidate;
    private boolean score;

    public VoteDTO() {
    }

    public VoteDTO(String idJudge, String idCandidate, boolean score) {
        this.idJudge = idJudge;
        this.idCandidate = idCandidate;
        this.score = score;
    }

    public String getIdJudge() {
        return idJudge;
    }

    public void setIdJudge(String idJudge) {
        this.idJudge = idJudge;
    }

    public String getIdCandidate() {
        return idCandidate;
    }

    public void setIdCandidate(String idCandidate) {
        this.idCandidate = idCandidate;
    }

    public boolean isScore() {
        return score;
    }

    public void setScore(boolean score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idCandidate == null) ? 0 : idCandidate.hashCode());
        result = prime * result + ((idJudge == null) ? 0 : idJudge.hashCode());
        result = prime * result + (score ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VoteDTO other = (VoteDTO) obj;
        if (idCandidate == null) {
            if (other.idCandidate != null)
                return false;
        } else if (!idCandidate.equals(other.idCandidate))
            return false;
        if (idJudge == null) {
            if (other.idJudge != null)
                return false;
        } else if (!idJudge.equals(other.idJudge))
            return false;
        if (score != other.score)
            return false;
        return true;
    }

}
