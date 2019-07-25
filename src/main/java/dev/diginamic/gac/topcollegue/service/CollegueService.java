package dev.diginamic.gac.topcollegue.service;

import dev.diginamic.gac.topcollegue.controller.dto.CandidatClassementDto;
import dev.diginamic.gac.topcollegue.domain.Collegue;
import dev.diginamic.gac.topcollegue.domain.Vote;
import dev.diginamic.gac.topcollegue.persistence.CollegueRepository;
import dev.diginamic.gac.topcollegue.persistence.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollegueService {

    @Autowired
    private CollegueRepository collRepository;

    @Autowired
    private VoteRepository voteRepository;

    public List<CandidatClassementDto> getClassement() {

        List<Vote> votes = voteRepository.findAll();
        List<Collegue> candidats = collRepository.findAll();

        return candidats.stream()
                .map(collegue -> {
                    Integer score = 0;
                    List<Vote> votesCandidat = votes.stream()
                            .filter(vote -> vote.getCle().getCandidate().equals(collegue))
                            .collect(Collectors.toList());
                    for(Vote vote : votesCandidat) {
                        score += vote.getScore()? 2 : -1;
                    }
                    return new CandidatClassementDto(collegue.getPictureUrl(), collegue.getLastName(), collegue.getFirstName(), score);
                })
                .collect(Collectors.toList());
    }
}
