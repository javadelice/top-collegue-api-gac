package dev.diginamic.gac.topcollegue.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.diginamic.gac.topcollegue.domain.Vote;
import dev.diginamic.gac.topcollegue.util.ClePrimaireComposite;

public interface VoteRepository extends JpaRepository<Vote, ClePrimaireComposite> {

    public Optional<Vote> findByKey(ClePrimaireComposite key);

}
