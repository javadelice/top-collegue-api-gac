package dev.diginamic.gac.topcollegue.service;

import dev.diginamic.gac.topcollegue.controller.dto.CandidatClassementDto;
import dev.diginamic.gac.topcollegue.domain.Collegue;
import dev.diginamic.gac.topcollegue.domain.Vote;
import dev.diginamic.gac.topcollegue.persistence.CollegueRepository;
import dev.diginamic.gac.topcollegue.persistence.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class CollegueService {
    @Autowired
    private CollegueRepository collegueRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VoteRepository voteRepository;

    public List<CandidatClassementDto> getClassement() {

        List<Vote> votes = voteRepository.findAll();
        List<Collegue> candidats = collegueRepository.findAll();

        return candidats.stream()
                .map(collegue -> {
                    int score = 0;
                    List<Vote> votesCandidat = votes.stream()
                            .filter(vote -> vote.getCle().getCandidate().equals(collegue))
                            .collect(Collectors.toList());
                    for(Vote vote : votesCandidat) {
                        score += vote.getScore()? 2 : -1;
                    }
                    return new CandidatClassementDto(collegue.getPictureUrl(), collegue.getLastName(), collegue.getFirstName(), score);
                })
                .sorted((candidat1, candidat2) -> candidat2.getScore() - candidat1.getScore())
                .collect(Collectors.toList());
    }

	// *** RECHERCHER PAR NOM ****
	public Optional<Collegue> rechercheParUsername(String name) {
		return collegueRepository.findByUsername(name);
	}

}
