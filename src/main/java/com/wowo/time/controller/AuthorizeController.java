package com.wowo.time.controller;

import com.wowo.time.dto.AccessTokenDTO;
import com.wowo.time.dto.GithubUser;
import com.wowo.time.mapper.UserMapper;
import com.wowo.time.model.User;
import com.wowo.time.provider.GithubProvider;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
     @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code ,
                           @RequestParam(name="state")String state,
                             HttpServletRequest request ,
                              HttpServletResponse Response){
        AccessTokenDTO accessTokenDTO= new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRediret_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setState(state);
        String accessToken= githubProvider.getAccessToken(accessTokenDTO);
         GithubUser githubUser=githubProvider.getUser(accessToken);
         System.out.println(githubUser);
         if(githubUser!=null)
         {
             //登录成功
            User user=new User();
            String token=UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtModified());

             userMapper.insert(user);
            Response.addCookie(new Cookie("token", token));
             request.getSession().setAttribute("user",githubUser);
             return "redirect:/";

         }else {
             //登录失败
             return "redirect:/";
         }

    }
}
