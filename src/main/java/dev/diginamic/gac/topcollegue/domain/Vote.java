package dev.diginamic.gac.topcollegue.domain;

import java.util.List;

//import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.Table;

import dev.diginamic.gac.topcollegue.util.ClePrimaireComposite;

@Entity
@Table
public class Vote {

    @EmbeddedId
    protected ClePrimaireComposite cle = new ClePrimaireComposite();

//    private Collegue judge;
//    private Collegue candidate;
    private Boolean score;

    public Vote() {
    }

    public Vote(ClePrimaireComposite cle, Boolean score, List<Collegue> collegue) {
        this.cle = cle;
        this.score = score;
    }

//    public Vote(Collegue judge, Collegue candidate, Boolean score) {
//        this.judge = judge;
//        this.candidate = candidate;
//        this.score = score;
//    }

//    public Collegue getJudge() {
//        return judge;
//    }

//    public void setJudge(Collegue judge) {
//        this.judge = judge;
//    }

//    public Collegue getCandidate() {
//        return candidate;
//    }

//    public void setCandidate(Collegue candidate) {
//        this.candidate = candidate;
//    }

    public Boolean getScore() {
        return score;
    }

    public ClePrimaireComposite getCle() {
        return cle;
    }

    public void setCle(ClePrimaireComposite cle) {
        this.cle = cle;
    }

    public void setScore(Boolean score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cle == null) ? 0 : cle.hashCode());
        result = prime * result + ((score == null) ? 0 : score.hashCode());
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
        Vote other = (Vote) obj;
        if (cle == null) {
            if (other.cle != null)
                return false;
        } else if (!cle.equals(other.cle))
            return false;
        if (score == null) {
            if (other.score != null)
                return false;
        } else if (!score.equals(other.score))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Vote [cle=");
        builder.append(cle);
        builder.append(", score=");
        builder.append(score);
        builder.append("]");
        return builder.toString();
    }

}
