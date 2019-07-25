package dev.diginamic.gac.topcollegue.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import dev.diginamic.gac.topcollegue.controller.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.diginamic.gac.topcollegue.domain.Collegue;
import dev.diginamic.gac.topcollegue.persistence.CollegueRepository;
import dev.diginamic.gac.topcollegue.service.CollegueService;
import io.jsonwebtoken.Jwts;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(allowCredentials = "true")
@RestController
public class AuthController {

	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN;

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Value("${jwt.secret}")
	private String SECRET;

	@Value("${collegue.api.url}")
	private String API_URL;

	@Autowired
	private CollegueService collegueService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CollegueRepository collegueRepository;

	public AuthController(CollegueRepository collegueRepository, PasswordEncoder passwordEncoder) {
		this.collegueRepository = collegueRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping(value = "/auth")
	public ResponseEntity<?> authenticate(@RequestBody Collegue infos) {
		Optional<Collegue> optionnalCollegue = this.collegueRepository.findByUsername(infos.getUsername());

		// Recherche dans le deuxieme back si le collegue n'existe pas (collegue-api)
		if (!optionnalCollegue.isPresent()) {
			RestTemplate restTemplate = new RestTemplate();
			try {
				// Recuperation des cookies (si la connexion reussie)
				List<String> cookies = restTemplate.postForEntity(API_URL + "/auth", infos, String.class).getHeaders().get("Set-Cookie");
				String cookie = cookies.get(0).substring(0, cookies.get(0).indexOf(';'));

				// Requete /me pour recuperer les donnees du collegue
				HttpHeaders headers = new HttpHeaders();
				headers.set("Cookie", cookie);
				HttpEntity<UserDto> entity = new HttpEntity<>(headers);
				UserDto user = restTemplate.exchange(API_URL + "/me", HttpMethod.GET, entity, UserDto.class).getBody();

				// On ne prend pas en compte les utilisateurs qui ne sont pas des collegues
				if(user != null && user.getMatricule() != null) {

					// Creation d'un collegue a partir des donnees du /me
					Collegue collegue = new Collegue(user.getMatricule(), user.getUsername(), passwordEncoder.encode(infos.getPassword()), user.getPictureUrl(), user.getLastName(), user.getFirstName());

					// Maj de la photo si besoin
					if (infos.getPictureUrl() != null && !infos.getPictureUrl().equals("")) {
						collegue.setPictureUrl(infos.getPictureUrl());
					}

					// sauvegarde dans la base, puis mise au format optionnal pour la suite
					collegueRepository.save(collegue);
					optionnalCollegue = Optional.of(collegue);
				}

			} catch (HttpClientErrorException e) {
				// en cas d'erreur http vers le second back, la connexion echouera (401 - unauthorized)
			}
		}


		return optionnalCollegue
		        .filter(utilisateur -> passwordEncoder.matches(infos.getPassword(), utilisateur.getPassword()))
		        .map(utilisateur -> {

			        String jetonJWT = Jwts.builder()
			                .setSubject(utilisateur.getUsername())
			                .setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
			                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET)
			                .compact();

			        ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE, jetonJWT)
			                .httpOnly(true)
			                .maxAge(EXPIRES_IN * 1000)
			                .path("/")
			                .build();

			        return ResponseEntity.ok()
			                .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
			                .build();
		        })

		        .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

	}
}
