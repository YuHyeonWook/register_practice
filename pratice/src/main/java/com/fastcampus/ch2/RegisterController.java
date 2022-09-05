package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class RegisterController {


    @GetMapping("/register/add")
    public String register() {
        return "registerForm";
    }

    @PostMapping("/register/save")
    public String regisetLog(User user, Model m) throws Exception {
        //1. 유효성 검사
        if(!isValid(user)) {   //isValid() 유효성검사에서  회원가입이 true가 나오면 home으로 가고 false가 되면 registerInfo을 보여줘서 회원가입에 성공한다.
            String msg = URLEncoder.encode( "잘못된 id 입니다.", "utf-8");

            m.addAttribute("msg",msg);
            return "redirect:/register/add";
        }

        return "registerInfo";
    }

    private boolean isValid(User u) {
        return true;
    }
}