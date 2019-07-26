package dev.diginamic.gac.topcollegue.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.diginamic.gac.topcollegue.controller.DTO.CandidatVoteDto;
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

    public List<CandidatVoteDto> getCandidats(String username) {

        Collegue user = collRepository.findByUsername(username).get();
        List<Vote> votes = voteRepository.findAll().stream()
                .filter(vote -> vote.getKey().getJudge().equals(user))
                .collect(Collectors.toList());

        return collRepository.findAll().stream()
                .map(collegue -> {
                    CandidatVoteDto candidate = new CandidatVoteDto();
                    candidate.setId(collegue.getId());
                    candidate.setLastName(collegue.getLastName());
                    candidate.setFirstName(collegue.getFirstName());
                    candidate.setPictureUrl(collegue.getPictureUrl());

                    for(int i = 0; i < votes.size(); i++) {
                        if (votes.get(i).getKey().getCandidate().equals(collegue)) {
                            candidate.setScore(votes.get(i).getScore());
                        }
                    }
                    return candidate;
                })
                .collect(Collectors.toList());
    }
}
