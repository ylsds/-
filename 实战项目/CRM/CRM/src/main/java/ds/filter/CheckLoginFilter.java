package ds.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.annotation.Target;

@WebFilter(urlPatterns = {"/*/*","*.jsp","*.html"})
public class CheckLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
       String servletPath=request.getServletPath();
        HttpSession session=request.getSession(false);
        if ("/index/login.jsp".equals(servletPath)||"/workbench/index.jsp".equals(servletPath)||"/user/login.do".equals(servletPath)||(session!=null && session.getAttribute("user")!=null)){
            filterChain.doFilter(request,response);
        }else{
            response.sendRedirect("/crm/" );
        }
    }

    @Override
    public void destroy() {

    }
}
