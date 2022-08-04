package fr.m2i.login.servlets;

import fr.m2i.login.crud.CrudNews;
import fr.m2i.login.crud.CrudUser;
import fr.m2i.login.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {

    private static final String PAGE = "/WEB-INF/pages/pageHome.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CrudNews crudNews = new CrudNews();
        request.setAttribute("news",crudNews.selectLastX(5));

        this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("log").equals("logout")){
            ((HttpServletRequest) request).getSession().removeAttribute("userIsLogged");
        }

        doGet(request,response);
    }
}
