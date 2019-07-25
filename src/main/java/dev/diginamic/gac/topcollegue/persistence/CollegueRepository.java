package dev.diginamic.gac.topcollegue.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.diginamic.gac.topcollegue.domain.Collegue;

public interface CollegueRepository extends JpaRepository <Collegue, String>{

}
