package dev.diginamic.gac.topcollegue.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.diginamic.gac.topcollegue.controller.dto.CandidatClassementDto;
import dev.diginamic.gac.topcollegue.controller.dto.CandidatVoteDto;
import dev.diginamic.gac.topcollegue.controller.dto.VoteDto;
import dev.diginamic.gac.topcollegue.domain.Collegue;
import dev.diginamic.gac.topcollegue.service.CollegueService;

@CrossOrigin(allowCredentials = "true")
@RestController
public class CollegueController {

    @Autowired
    private CollegueService collegueService;

    @RequestMapping(method = RequestMethod.POST, path = "/vote")
    public VoteDto voter(@RequestBody VoteDto vote) {
       return collegueService.voter(vote, SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping(method = RequestMethod.GET, path = "votes")
    public List<CandidatVoteDto> getCandidats() {
        return collegueService.getCandidats(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping(
            path = "classement",
            method = RequestMethod.GET
    )
    public List<CandidatClassementDto> classement() {
        return collegueService.getClassement();
    }

	@RequestMapping(method = RequestMethod.GET, path = "/me")
	public Optional<Collegue> retourneCollegue() {
		return collegueService.rechercheParUsername(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
