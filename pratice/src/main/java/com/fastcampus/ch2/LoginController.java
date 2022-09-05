package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {
    //@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
    @GetMapping ("/login")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(String id, String pwd, boolean rememberId, Model m, HttpServletResponse response) throws Exception{
        System.out.println("id = " + id);
        System.out.println("pwd = " + pwd);
        System.out.println("rememberId = " + rememberId);

        //1. id와pwd 확인한다.
        if(!loginCheck(id,pwd)) {
            //2. id와 pwd가 일치하지 않으면 loginForm으로 되돌아간다.
            String msg = URLEncoder.encode( "id와 pwd가 일치하지 않습니다.","utf-8");

            m.addAttribute("msg", msg);
            return "redirect:/login/login";
        }
        //3. id와 pwd가 일치하면
        if(rememberId) {
            // 4.rememberId가 true이면 쿠키를 생성하고 (아이디 기억을 누르고 로그인하면 쿠키를 생성하고)
            Cookie cookie = new Cookie("id",id);
            // 5. 응답에 쿠키를 저장한다.
            response.addCookie(cookie);
        } else {
            // 6. rememberId가 false이면 쿠키를 생성하지 않는다. (아이디 기억을 누르지 않고 로그인하면  쿠키를 생성x)
            Cookie cookie = new Cookie("id",id);
            // 쿠키를 삭제할때 
            cookie.setMaxAge(0);
            // 응답에 쿠키를 저장한다.
            response.addCookie(cookie);
        }

        // 6.게시글 home으로 이동함
        return "redirect:/";
    }

    private boolean loginCheck(String id, String pwd) {
//        return "asdf".equals(id) && "1234".equals(pwd);
        return true;
    }

    
}
