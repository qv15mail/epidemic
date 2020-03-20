package com.supwisdom.datashow.filter;

import com.supwisdom.datashow.system.domain.Menu;
import com.supwisdom.datashow.system.service.UserAndRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "RemoveUrlJSessionIdFilter",urlPatterns = {"/*"})
public class RemoveUrlJSessionIdFilter implements Filter {
    @Autowired
    private UserAndRoleService userAndRoleService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        if (!(request instanceof HttpServletRequest)){
            chain.doFilter(request,response);
            return;
        }
        //从url中删除jsessionid
        if (httpServletRequest.isRequestedSessionIdFromURL()){
            HttpSession session = httpServletRequest.getSession();
            if (null != session){
                session.invalidate();
            }
        }
        HttpServletResponseWrapper wrapperResponse = new HttpServletResponseWrapper(httpServletResponse){
            @Override
            public String encodeURL(String url) {
                return url;
            }

            @Override
            public String encodeRedirectURL(String url) {
                return url;
            }

        };
        //获取请求页面
        HttpServletRequest hreq=(HttpServletRequest)request;
        String reqPath=hreq.getServletPath();
        HttpSession session = hreq.getSession();
        //判断页面信息，登录user有信息，登录页面时login.jsp或者是prologin.jsp
        if(session.getAttribute("ids")!=null && reqPath.contains("mu_")){
            String uids=(String)session.getAttribute("ids");
            List<Menu> pms = userAndRoleService.getMenuBy(uids,reqPath);
            if (pms.size()==0) {
                //根据ids查询出相应的菜单权限，判断请求菜单是否在菜单列表里面，不存在则跳转到首页
                request.setAttribute("tips", "没有权限！");
                ((HttpServletResponse) response).sendRedirect("/basedata/userPortrait");
                response.flushBuffer();
            }
        }

        chain.doFilter(request,wrapperResponse);

    }

    @Override
    public void destroy() {

    }
}
