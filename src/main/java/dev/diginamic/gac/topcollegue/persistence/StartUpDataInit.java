package dev.diginamic.gac.topcollegue.persistence;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.diginamic.gac.topcollegue.domain.Collegue;
import dev.diginamic.gac.topcollegue.domain.Vote;
import dev.diginamic.gac.topcollegue.util.ClePrimaireComposite;

@Component
public class StartUpDataInit {

    @Autowired
    private CollegueRepository collRepository;
    
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        Collegue collegue1 = new Collegue(UUID.randomUUID().toString(), "clement", passwordEncoder.encode("clement"),
                "https://vignette.wikia.nocookie.net/jjba/images/7/7f/Spice_Girl_infobox.png/revision/latest/scale-to-width-down/310?cb=20180517101701&path-prefix=fr",
                "Ormaux", "Clement");
        collRepository.save(collegue1);

        Collegue collegue2 = new Collegue(UUID.randomUUID().toString(), "adrien", passwordEncoder.encode("adrien"),
                "https://vignette.wikia.nocookie.net/jjba/images/9/9f/Crazy_Diamond_Manga.Infobox.png/revision/latest/scale-to-width-down/310?cb=20180622215653&path-prefix=fr",
                "Chauvin", "Adrien");
        collRepository.save(collegue2);
        
        collRepository.save(new Collegue(UUID.randomUUID().toString(), "glen", passwordEncoder.encode("glen"),
                "https://vignette.wikia.nocookie.net/jjba/images/1/14/Gold_Experience_color.png/revision/latest?cb=20180417125730&path-prefix=fr",
                "Ollivier", "Glen"));
        
        
        
        Vote vote1 = new Vote(new ClePrimaireComposite(collegue1,collegue2), false);
        voteRepository.save(vote1);
        
        Vote vote2 = new Vote(new ClePrimaireComposite(collegue2,collegue1), false);
        voteRepository.save(vote2);
        
        Vote vote3 = new Vote(new ClePrimaireComposite(collegue1,collegue2), true);
        voteRepository.save(vote3);
        
    }

}
