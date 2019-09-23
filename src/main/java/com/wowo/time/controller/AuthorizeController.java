package com.wowo.time.controller;

import com.wowo.time.dto.AccessTokenDTO;
import com.wowo.time.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
     @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code ,
                           @RequestParam(name="state")String state){
        AccessTokenDTO accessTokenDTO= new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRediret_uri("http://localhost:8080/callback");
        accessTokenDTO.setClient_id("Iv1.5aa8d15383c0ccb4");
        accessTokenDTO.setClient_secret("5b090d8026e6d02d59b2ab171a6939b26133a1dc");
        accessTokenDTO.setState(state);
         githubProvider.getAccessToken(accessTokenDTO);
        return "index";

    }
}
