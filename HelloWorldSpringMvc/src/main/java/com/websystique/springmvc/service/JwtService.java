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
		 
		  https://www.baeldung.com/java-json-web-tokens-jjwt
		 
		  JWT specification has seven of these specified as �registered� claims. They are:

				iss	Issuer
				sub	Subject
				aud	Audience
				exp	Expiration
				nbf	Not Before
				iat	Issued At
				jti	JWT ID
		  When building a JWT, you can put in any custom claims you wish. The list above simply represents 
		  the claims that are reserved both in the key that is used and the expected type. 
		  Our CSRF has a JWT ID, an �Issued At� time, a �Not Before� time, and an Expiration time. 
		  The expiration time is exactly one minute past the issued at time.
		  
		  **
		  When a JWT token is generated, there is a secret that is used to generate the token. 
		  Only the server should know this secret. If someone were to modify the data contained in the JWT, 
		  the server would fail to decode it. This means the server can trust any JWT that it can decode 
		  and verify. 
		 
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
