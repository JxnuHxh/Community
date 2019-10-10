package com.wowo.time.controller;

import com.wowo.time.mapper.QuestionMapper;
import com.wowo.time.mapper.UserMapper;
import com.wowo.time.model.Question;
import com.wowo.time.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class Publishcontroller {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;



    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,

                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model
    ){
        User user=null;
        Cookie[] cookies= request.getCookies();
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals("token")){
                String token= cookie.getValue();
                user=userMapper.findByYoken(token);
                if(user!=null)
                {
                    request.getSession().setAttribute("user",user);
                }
                break;
            }

        }
        if(user == null)
        {
            model.addAttribute("error","用户未登录");
            return "publish";

        }
        Question question =new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtModified());

        questionMapper.create(question);

        return "redirect:/";

    }

}
