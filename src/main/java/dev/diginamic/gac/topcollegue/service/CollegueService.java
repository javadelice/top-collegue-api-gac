package dev.diginamic.gac.topcollegue.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.diginamic.gac.topcollegue.domain.Collegue;
import dev.diginamic.gac.topcollegue.persistence.CollegueRepository;

@Service
public class CollegueService {

	@Autowired
	private CollegueRepository collegueRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// *** RECHERCHER PAR NOM ****
	public Optional<Collegue> rechercheParUsername(String name) {
		return collegueRepository.findByUsername(name);
	}

}
