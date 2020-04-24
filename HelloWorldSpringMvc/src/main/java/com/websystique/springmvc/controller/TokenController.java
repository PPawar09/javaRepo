package com.websystique.springmvc.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springmvc.domain.MinimalProfile;
import com.websystique.springmvc.exceptions.FailedToLoginException;
import com.websystique.springmvc.jwt.LoginCredentials;
import com.websystique.springmvc.service.JwtService;
import com.websystique.springmvc.service.LoginService;

/**
 * This Class is the one which generate the token and append in the response as header. which furthure will be used
 * for calling secured operation.
 * @author ppawar2
 *
 */

@RestController
@RequestMapping(path = "/gettoken")
public class TokenController {

    private final LoginService loginService;
    private final JwtService jwtService;

    @SuppressWarnings("unused")
    public TokenController() {
        this(null, null);
    }

    @Autowired
    public TokenController(LoginService loginService, JwtService jwtService) {
        this.loginService = loginService;
        this.jwtService = jwtService;
    }
    
    
    /*
     * Test Reqiuest url http://localhost:8080/HelloWorldSpringMvc/gettoken
     * 	
     * {
			"username":"greenostrich307",
			"password":"darkange"
		}
     * 
     * 
     * 
     */

    @RequestMapping(path = "",
            method = POST,
            produces = APPLICATION_JSON_VALUE)
    public MinimalProfile login(@RequestBody LoginCredentials credentials,
                                HttpServletResponse response) {
        return loginService.login(credentials)
                .map(minimalProfile -> {
                    try {
                    	String token = jwtService.tokenFor(minimalProfile);
                    	System.out.println("** Token Generated **  "+ token);
                    	
                    	decodeJwt(token);
                    	
                    	//this will generate jwt token and append as header in response
                        response.setHeader("Token", jwtService.tokenFor(minimalProfile));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return minimalProfile;
                })
                .orElseThrow(() -> new FailedToLoginException(credentials.getUsername()));
    }
    
    public void decodeJwt(String jwtToken){
    	
            System.out.println("------------ Decode JWT ------------");
            String[] split_string = jwtToken.split("\\.");
            String base64EncodedHeader = split_string[0];
            String base64EncodedBody = split_string[1];
            String base64EncodedSignature = split_string[2];

            System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
            Base64 base64Url = new Base64(true);
            String header = new String(base64Url.decode(base64EncodedHeader));
            System.out.println("JWT Header : " + header);


            System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
            String body = new String(base64Url.decode(base64EncodedBody));
            System.out.println("JWT Body : "+body); 
            
            System.out.println("~~~~~~~~~ JWT Signature ~~~~~~~");
            String sig = new String(base64Url.decode(base64EncodedSignature));
            System.out.println("JWT Sig : "+sig);
            
            
        }
    
}
