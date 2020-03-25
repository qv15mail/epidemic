package com.supwisdom.datashow.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "loginFilter",urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    String NO_LOGIN = "您还未登录";
    String[] staticPaths = new String[]{"/alte","/bootstrap","/css","/devfull","/font-awesome","/images","/logicons","/js","/plugins","/favicon"};
    String[] includeUrls = new String[]{"/login/login","/","/logout","/df","/yy"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest)request;
        HttpServletResponse servletResponse = (HttpServletResponse)response;
        HttpSession session = servletRequest.getSession(false);
        String uri = servletRequest.getRequestURI();

        //是否需要过滤
        boolean needFilter = isNeedFilter(uri);

        if (!needFilter){//不需要过滤传到下一个过滤器
            chain.doFilter(request,response);
        }else {//需要过滤
            //session不为空，且session包含user对象则为登陆状态
            if (session != null && session.getAttribute("user") != null){
                chain.doFilter(servletRequest,servletResponse);
            }else {
                String requestType = servletRequest.getHeader("X-Requested-With");
                //判断是否是ajax请求
                if (requestType!=null && "XMLHttpRequest".equals(requestType)){
                    servletResponse.getWriter().write(this.NO_LOGIN);
                }else {
                    //重定向到登录页
                    servletResponse.sendRedirect("/");
                }
                return;
            }
        }

    }

    @Override
    public void destroy() {

    }

    public boolean  isNeedFilter(String uri){
        for (String staticPath:staticPaths) {
            if (uri.contains(staticPath)) {
                return false;
            }
        }
        for (String includeUrl : includeUrls)
            if (includeUrl.equals(uri)){
                return false;
            }
            return true;
    }
}
