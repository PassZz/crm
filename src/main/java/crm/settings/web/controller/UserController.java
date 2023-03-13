package crm.settings.web.controller;

import crm.commons.constants.Constants;
import crm.commons.domain.ReturnObject;
import crm.commons.utils.DateUtils;
import crm.settings.domain.User;
import crm.settings.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    /*
        url要和controller方法处理完请求之后，响应信息返回的页面的资源目录保持一致
     */
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(HttpServletRequest request){
        //请求转发到登录页面
        return "settings/qx/user/login";
    }

    /*
        把控制层代码中处理好的数据，传递到视图层，使用作用域传递
        pageContext：用来在同一个页面的不同标签之间传递数据
        request：在同一个请求过程中间传递数据
        session：同一个浏览器窗口的不同请求之间传递数据
        application：所有用户都共享的数据，并且长久频繁的数据
     */
    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    //public @ResponseBody(写在这边也可以) Object login(){
    public Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        //封装参数
        Map<String, Object> map = new HashMap<>();
        //封装的key的值需要和数据库的key的值保持一致
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);
        //调用service层方法查询用户
        User user = userService.queryUserByLoginActAndPwd(map);
        //根据查询结果生成响应信息
        ReturnObject returnObject = new ReturnObject();
        if (user == null){
            //登陆失败，用户名或密码错误
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("登陆失败，用户名或密码错误");
        }else {     //进一步判断账号是否合法
            if (DateUtils.formatDateTime(new Date()).compareTo(user.getExpireTime()) > 0){
                //登陆失败，账号已过期
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登陆失败，账号已过期");
            }else if ("0".equals(user.getLockState())){
                //登陆失败，状态被锁定
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登陆失败，状态被锁定");
            }else if (!user.getAllowIps().contains(request.getRemoteAddr())){
                //登陆失败，ip受限
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登陆失败，ip受限");
            }else {
                //登录成功
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);

                //把user对象把存到session中
                session.setAttribute(Constants.SESSION_USER, user);

                //判断用户是否选择了十天内免登录，如果选择了的话，isRemPwd的值为true
                //如果需要记住密码，则往外写cookie
                if ("true".equals(isRemPwd)){
                    Cookie loginActCookie = new Cookie("loginAct", user.getLoginAct());
                    loginActCookie.setMaxAge(60 * 60 * 24 * 10);
                    response.addCookie(loginActCookie);
                    Cookie loginPwdCookie = new Cookie("loginPwd", user.getLoginPwd());
                    loginPwdCookie.setMaxAge(60 * 60 * 24 * 10);
                    response.addCookie(loginPwdCookie);
                }else { //如果用户取消选择十天内免登录，则将cookie清除，他下次登录的时候，不会记住用户名和密码
                    //把没有过期的cookie删除
                    Cookie loginActCookie = new Cookie("loginAct", "1");
                    loginActCookie.setMaxAge(0);
                    response.addCookie(loginActCookie);
                    Cookie loginPwdCookie = new Cookie("loginPwd", "1");
                    loginPwdCookie.setMaxAge(0);
                    response.addCookie(loginPwdCookie);

                }
            }
        }
        return returnObject;
    }
}
