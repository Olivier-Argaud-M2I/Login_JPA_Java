package fr.m2i.login.filtres;

import fr.m2i.login.crud.CrudUser;
import fr.m2i.login.models.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@WebFilter({"/user"} )
public class LoginFilter implements Filter {

    private static final String PAGE_HOME = "/WEB-INF/pages/pageHome.jsp";
    private static final String PAGE_USER = "/WEB-INF/pages/pageUser.jsp";

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        User user = null;
        User user1 = null;
        if(request.getParameter("username")!=null&&request.getParameter("password")!=null){
            user = new User( request.getParameter("username"),request.getParameter("password"));
            CrudUser crudUser = new CrudUser();
            user1 = crudUser.findByName(user.getUsername());
        }

        if(user1!=null && user !=null && user1.getPassword().equals(user.getPassword())) {
            ((HttpServletRequest) request).getSession().setAttribute("userIsLogged" , true);

        }

        if(((HttpServletRequest) request).getSession().getAttribute("userIsLogged")!=null){
            chain.doFilter(request,response);
        }
        else{
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("/login/home");
        }
    }
}
