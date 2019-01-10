package com.websystique.springmvc.service;

import static java.time.ZoneOffset.UTC;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.websystique.springmvc.domain.MinimalProfile;
import com.websystique.springmvc.jwt.SecretKeyProvider;

@Component
public class JwtService {
    private static final String ISSUER = "in.sdqali.jwt";
    public static final String USERNAME = "username";
    private final SecretKeyProvider secretKeyProvider;
    private final ProfileService profileService;

    @SuppressWarnings("unused")
    public JwtService() {
        this(null, null);
    }

    @Autowired
    public JwtService(SecretKeyProvider secretKeyProvider, ProfileService profileService) {
        this.secretKeyProvider = secretKeyProvider;
        this.profileService = profileService;
    }

    /*
     * It consist of three parts each separated with a dot(.).
     * 
     * - First part is header which Base64 encoded. 
     * 	 After decoding we will get something like { "alg": "HS256", //Algorithm used "typ": "JWT" }

	   - Second part is claims and Base64 encoded. After decoding we will get something like 
	   	 { "sub": "1234567890", "name": "John Doe", "admin": true }

	   - Third part is signature and is generated with
		 HMACSHA256( base64UrlEncode(header) + "." + base64UrlEncode(payload), secret base64 encoded )
		 
     * 
     * 
     * 
     */
    public String tokenFor(MinimalProfile minimalProfile) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();
        String roundTrip = new String(secretKey, "UTF8");
        System.out.println("**SecKey**" + secretKey.toString());
        
        Date expiration = Date.from(LocalDateTime.now(UTC).plusHours(2).toInstant(UTC));
        return Jwts.builder()
                .setSubject(minimalProfile.getUsername())
                .claim("UserName", minimalProfile.getUsername())
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Optional<MinimalProfile> verify(String token) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return profileService.minimal(claims.getBody().getSubject().toString());
    }
}
