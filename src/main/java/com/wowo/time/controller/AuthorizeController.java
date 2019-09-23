package com.wowo.time.controller;

import com.wowo.time.dto.AccessTokenDTO;
import com.wowo.time.dto.GithubUser;
import com.wowo.time.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
     @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code ,
                           @RequestParam(name="state")String state){
        AccessTokenDTO accessTokenDTO= new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRediret_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setState(state);
        String accessToken= githubProvider.getAccessToken(accessTokenDTO);
         GithubUser user=githubProvider.getUser(accessToken);
         System.out.println(user.getName());
        return "index";

    }
}
