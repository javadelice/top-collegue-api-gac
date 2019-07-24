package dev.diginamic.gac.topcollegue.domain;

import java.util.Objects;

public class Vote {
    private Collegue judge;
    private Collegue candidate;
    private Boolean score;

    public Vote() {
    }

    public Vote(Collegue judge, Collegue candidate, Boolean score) {
        this.judge = judge;
        this.candidate = candidate;
        this.score = score;
    }

    public Collegue getJudge() {
        return judge;
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
        Vote vote = (Vote) o;
        return Objects.equals(judge, vote.judge) &&
                Objects.equals(candidate, vote.candidate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(judge, candidate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vote{");
        sb.append("judge=").append(judge);
        sb.append(", candidate=").append(candidate);
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }
}
