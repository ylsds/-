package ds.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getServletPath());
        if ("/index/login.jsp".equals(request.getServletPath())||request.getSession(false).getAttribute("user")!=null){
            return true;
        }
        response.sendRedirect("/index/login.jsp");
        return false;

    }
}
