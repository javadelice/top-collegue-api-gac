package dev.diginamic.gac.topcollegue.controller;


import dev.diginamic.gac.topcollegue.controller.dto.CandidatClassementDto;
import dev.diginamic.gac.topcollegue.service.CollegueService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import dev.diginamic.gac.topcollegue.domain.Collegue;

@CrossOrigin(allowCredentials = "true")
@RestController
public class CollegueController {
    @Autowired
    private CollegueService collegueService;

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
