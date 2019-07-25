package dev.diginamic.gac.topcollegue.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.diginamic.gac.topcollegue.domain.Collegue;
import dev.diginamic.gac.topcollegue.persistence.CollegueRepository;
import dev.diginamic.gac.topcollegue.service.CollegueService;
import io.jsonwebtoken.Jwts;

@CrossOrigin(allowCredentials = "true")
@RestController
public class AuthController {

	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN;

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Value("${jwt.secret}")
	private String SECRET;

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

		return this.collegueRepository.findByUsername(infos.getUsername())
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
