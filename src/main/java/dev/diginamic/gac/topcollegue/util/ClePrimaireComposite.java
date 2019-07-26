package dev.diginamic.gac.topcollegue.util;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import dev.diginamic.gac.topcollegue.domain.Collegue;

@Embeddable
public class ClePrimaireComposite implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    private Collegue judge;
    @ManyToOne
    private Collegue candidate;

    public Collegue getJudge() {
        return judge;
    }

    public ClePrimaireComposite() {
    }

    public ClePrimaireComposite(Collegue judge, Collegue candidate) {
        this.judge = judge;
        this.candidate = candidate;
    }

    public void setJudge(Collegue judge) {
        this.judge = judge;
    }

    public Collegue getCandidate() {
        return candidate;
    }

    public void setCandidate(Collegue candidate) {
        this.candidate = candidate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((candidate == null) ? 0 : candidate.hashCode());
        result = prime * result + ((judge == null) ? 0 : judge.hashCode());
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
        ClePrimaireComposite other = (ClePrimaireComposite) obj;
        if (candidate == null) {
            if (other.candidate != null)
                return false;
        } else if (!candidate.equals(other.candidate))
            return false;
        if (judge == null) {
            if (other.judge != null)
                return false;
        } else if (!judge.equals(other.judge))
            return false;
        return true;
    }

}
