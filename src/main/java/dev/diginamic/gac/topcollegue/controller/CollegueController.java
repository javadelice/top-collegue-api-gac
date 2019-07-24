package dev.diginamic.gac.topcollegue.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowCredentials = "true")
@RestController
public class CollegueController {

    @RequestMapping(
            method = RequestMethod.GET
    )
    public String helloWorld() {
        return "Hello World";
    }
}
