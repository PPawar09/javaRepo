package com.websystique.springmvc.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springmvc.domain.MinimalProfile;
import com.websystique.springmvc.exceptions.FailedToLoginException;
import com.websystique.springmvc.jwt.LoginCredentials;
import com.websystique.springmvc.service.JwtService;
import com.websystique.springmvc.service.LoginService;

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
                        response.setHeader("Token", jwtService.tokenFor(minimalProfile));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return minimalProfile;
                })
                .orElseThrow(() -> new FailedToLoginException(credentials.getUsername()));
    }
}
