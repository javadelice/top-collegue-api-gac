package dev.diginamic.gac.topcollegue.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.diginamic.gac.topcollegue.domain.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, String> {

	Optional<Collegue> findByUsername(String username);

}
