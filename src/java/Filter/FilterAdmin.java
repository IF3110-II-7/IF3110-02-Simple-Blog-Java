/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Filter;

import Model.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wira gotama
 */
public class FilterAdmin implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        if (user==null){
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("login.jsf");
        }
        else if (user.getRole().equals("admin")) {
            chain.doFilter(request, response);
        }
        else {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("login.jsf");
        }
    }

    @Override
    public void destroy() {
    }
}
