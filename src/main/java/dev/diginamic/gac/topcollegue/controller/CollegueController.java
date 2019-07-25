package dev.diginamic.gac.topcollegue.controller;

import dev.diginamic.gac.topcollegue.controller.dto.CandidatClassementDto;
import dev.diginamic.gac.topcollegue.service.CollegueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
