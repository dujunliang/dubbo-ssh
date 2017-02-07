package com.gov.sys.struts.interceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by dujunliang on 16/11/25.
 */
public class LoginFilter  extends HttpServlet implements Filter {


    public void destroy() {
    }

    public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) sRequest;
        HttpServletResponse response = (HttpServletResponse) sResponse;

        HttpSession session = request.getSession();
        String url=request.getServletPath();
        String contextPath=request.getContextPath();
        if(url.equals("")) url+="/";
        System.out.print(url.endsWith("/login"));
        if((url.endsWith("login"))){//若访问后台资源 过滤到login
            String user=(String)session.getAttribute("username");
            if(user==null){//转入管理员登陆页面
                response.sendRedirect(contextPath+"/login_login");
                return;
            }
        }
        filterChain.doFilter(sRequest, sResponse);
    }

    public void init(FilterConfig arg0) throws ServletException {

    }

    public static void main(String[] args) {

        String str = "/login_index";

        System.out.print(str.endsWith("login"));
    }

}
