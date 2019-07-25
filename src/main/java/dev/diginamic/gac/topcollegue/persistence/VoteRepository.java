package dev.diginamic.gac.topcollegue.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.diginamic.gac.topcollegue.domain.Vote;
import dev.diginamic.gac.topcollegue.util.ClePrimaireComposite;

public interface VoteRepository extends JpaRepository<Vote, ClePrimaireComposite>{

}
