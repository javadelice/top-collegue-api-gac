package dev.diginamic.gac.topcollegue.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.diginamic.gac.topcollegue.controller.DTO.VoteDTO;
import dev.diginamic.gac.topcollegue.domain.Collegue;
import dev.diginamic.gac.topcollegue.domain.Vote;
import dev.diginamic.gac.topcollegue.exception.CollegueNotFound;
import dev.diginamic.gac.topcollegue.persistence.CollegueRepository;
import dev.diginamic.gac.topcollegue.persistence.VoteRepository;
import dev.diginamic.gac.topcollegue.util.ClePrimaireComposite;

@Service
public class CollegueService {

    @Autowired
    private CollegueRepository collRepository;

    @Autowired
    private VoteRepository voteRepository;

    public CollegueService() {
    }

    public Collegue rechercherById(String id) throws CollegueNotFound {
        Optional<Collegue> collOpt = collRepository.findById(id);
        return collOpt.orElseThrow(() -> new CollegueNotFound("Collegue non trouvé"));
    }

    /**
     * Ajouter un vote à un candidat Le vote prit en compte sera le dernier effectué
     * 
     * @param key   qui est composé du matricule du collegue juge et du matricule du
     *              collegue candidat
     * @param score est un booleen
     * @return un nouveau vote constitué des collegue et du score
     */
    public VoteDTO voter(VoteDTO vote) {
        Vote unVote = new Vote();
        ClePrimaireComposite cle = new ClePrimaireComposite();
        cle.setJudge(collRepository.findById(vote.getIdJudge()).get());
        cle.setCandidate(collRepository.findById(vote.getIdCandidate()).get());
        unVote.setKey(cle);
        unVote.setScore(vote.isScore());

        voteRepository.save(unVote);

        return vote;
    }

}
