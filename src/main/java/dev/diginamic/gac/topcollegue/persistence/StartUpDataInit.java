package dev.diginamic.gac.topcollegue.persistence;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import dev.diginamic.gac.topcollegue.controller.DTO.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.diginamic.gac.topcollegue.domain.Collegue;
import dev.diginamic.gac.topcollegue.domain.Vote;
import dev.diginamic.gac.topcollegue.util.ClePrimaireComposite;
import org.springframework.web.client.RestTemplate;
import dev.diginamic.gac.topcollegue.controller.dto.UserDto;

@Component
public class StartUpDataInit {

    @Autowired
    private CollegueRepository collRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${collegue.api.url}")
    private String API_URL;

    @EventListener(ContextRefreshedEvent.class)
    public void init() {


        collRepository.save(new Collegue(UUID.randomUUID().toString(), "sega.sylla@gmail.com", passwordEncoder.encode("Sega"),
                "https://pbs.twimg.com/profile_images/1956226260/image.jpg",
                "SYLLA", "Sega"));


        RestTemplate restTemplate = new RestTemplate();
        List<String> cookies = restTemplate.postForEntity(API_URL + "/auth", new Collegue("", "root", "proot", "", "", ""), String.class).getHeaders().get("Set-Cookie");
        String cookie = cookies.get(0).substring(0, cookies.get(0).indexOf(';'));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        HttpEntity<List<AccountDto>> entity = new HttpEntity<>(headers);
        List<LinkedHashMap<String, String>> accounts = restTemplate.exchange(API_URL + "/collegues/accounts", HttpMethod.GET, entity, List.class).getBody();

        Random r = new Random();

        accounts.stream()
                .forEach(account -> {
                    if (r.nextBoolean() && !account.get("firstName").equals("Rossi")) {

                        List<String> cookiesAccount = restTemplate.postForEntity(API_URL + "/auth", new Collegue("", account.get("email"), account.get("firstName"), "", "", ""), String.class).getHeaders().get("Set-Cookie");
                        String cookieAccount = cookiesAccount.get(0).substring(0, cookiesAccount.get(0).indexOf(';'));

                        // Requete /me pour recuperer les donnees du collegue
                        HttpHeaders headersAccount = new HttpHeaders();
                        headersAccount.set("Cookie", cookieAccount);
                        HttpEntity<UserDto> entityAccount = new HttpEntity<>(headersAccount);
                        UserDto user = restTemplate.exchange(API_URL + "/me", HttpMethod.GET, entityAccount, UserDto.class).getBody();

                        if(user.getMatricule() != null) {
                            Collegue collegue = new Collegue(user.getMatricule(), user.getUsername(), passwordEncoder.encode(account.get("firstName")), user.getPictureUrl(), user.getLastName(), user.getFirstName());

                            collRepository.save(collegue);
                        }
                    }
                });

        List<Collegue> collegues = collRepository.findAll();

        collegues.forEach(juge ->
                collegues.forEach(candidat -> {
                if (!candidat.equals(juge) && r.nextBoolean()) {
                    Vote v = new Vote(new ClePrimaireComposite(juge, candidat), r.nextBoolean());
                    voteRepository.save(v);
                }
            })
        );
    }

}
