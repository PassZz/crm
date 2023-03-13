package crm.interceptors;

import crm.commons.constants.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptors implements HandlerInterceptor {

    //在控制器方法执行之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(Constants.SESSION_USER) != null){
            flag = true;
        }else {
            request.getRequestDispatcher("/notLoginAlert").forward(request, response);

        }
        return flag;
    }

    //在控制器方法执行之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    //在视图渲染之后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
