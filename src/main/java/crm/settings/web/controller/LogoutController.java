package crm.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    /*
        安全退出，销毁session并且删除cookie
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }
        Cookie loginActCookie = new Cookie("loginAct", "1");
        loginActCookie.setMaxAge(0);
        response.addCookie(loginActCookie);
        Cookie loginPwdCookie = new Cookie("loginPwd", "1");
        loginPwdCookie.setMaxAge(0);
        response.addCookie(loginPwdCookie);
        //跳转到登陆页面，因为使用到了thymeleaf视图解析器，不能直接访问settings/qx/user/toLogin.do进行跳转
        //需要加上redirect重定向视图，不走视图解析器
        //跳转到首页
        return "redirect:/";    //借助SpringMVC来重定向，response.sendRedirect("/crm/");   默认加上项目名称
    }
}
