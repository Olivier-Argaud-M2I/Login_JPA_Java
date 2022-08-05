package fr.m2i.login.servlets;

import fr.m2i.login.crud.CrudNews;
import fr.m2i.login.crud.CrudUser;
import fr.m2i.login.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;



@WebServlet(name = "HomeServlet", value = {"/home","/about","/contact","/login"})
public class HomeServlet extends HttpServlet {

    private static final String PAGE_HOME = "/WEB-INF/pages/pageHome.jsp";
    private static final String PAGE_ABOUT = "/WEB-INF/pages/pageAbout.jsp";
    private static final String PAGE_CONTACT = "/WEB-INF/pages/pageContact.jsp";
    private static final String PAGE_LOGIN = "/WEB-INF/pages/pageLogin.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("page")!=null) {
            switch (request.getParameter("page")) {
                case "home": {
                    CrudNews crudNews = new CrudNews();
                    request.setAttribute("news", crudNews.selectLastX(5));
                    this.getServletContext().getRequestDispatcher(PAGE_HOME).forward(request, response);
                    break;
                }
                case "about": {
                    this.getServletContext().getRequestDispatcher(PAGE_ABOUT).forward(request, response);
                    break;
                }
                case "contact": {
                    this.getServletContext().getRequestDispatcher(PAGE_CONTACT).forward(request, response);
                    break;
                }
                case "login": {

                }
                default: {
                    this.getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(request, response);
                }
            }
        }
        CrudNews crudNews = new CrudNews();
        request.setAttribute("news", crudNews.selectLastX(5));
        this.getServletContext().getRequestDispatcher(PAGE_HOME).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("log").equals("logout")){
            ((HttpServletRequest) request).getSession().removeAttribute("userIsLogged");
        }

        doGet(request,response);
    }
}
