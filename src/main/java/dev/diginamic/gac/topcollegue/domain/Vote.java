package dev.diginamic.gac.topcollegue.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.Table;

import dev.diginamic.gac.topcollegue.util.ClePrimaireComposite;

@Entity
@Table
public class Vote {

    @EmbeddedId
    protected ClePrimaireComposite key = new ClePrimaireComposite();

    private Boolean score;

    public Vote() {
    }

    public Vote(ClePrimaireComposite key, Boolean score) {
        this.key = key;
        this.score = score;
    }

    public Boolean getScore() {
        return score;
    }

    public ClePrimaireComposite getKey() {
        return key;
    }

    public void setKey(ClePrimaireComposite key) {
        this.key = key;
    }

    public void setScore(Boolean score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
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
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
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
        builder.append("Vote [key=");
        builder.append(key);
        builder.append(", score=");
        builder.append(score);
        builder.append("]");
        return builder.toString();
    }

}
