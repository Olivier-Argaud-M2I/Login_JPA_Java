package fr.m2i.login.servlets;

import fr.m2i.login.crud.CrudNews;
import fr.m2i.login.crud.CrudUser;
import fr.m2i.login.models.News;
import fr.m2i.login.models.User;
import fr.m2i.login.utils.Encrypt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {

    private static final String PAGE = "/WEB-INF/pages/pageUser.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CrudUser crudUser = new CrudUser();
        request.setAttribute("users",crudUser.selectAll());
        CrudNews crudNews = new CrudNews();
        request.setAttribute("news",crudNews.selectAll());

        this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");

        if(request.getParameter("type")!=null) {
            switch (type) {
                case "createNews": {
                    CrudNews crudNews = new CrudNews();
                    if(request.getParameter("id")!=null){
                        crudNews.update(new News(
                                Integer.valueOf(request.getParameter("id")),
                                request.getParameter("titre"),
                                request.getParameter("text"))
                        );
                    }
                    else{
                        crudNews.addNews(new News(
                                request.getParameter("titre"),
                                request.getParameter("text"))
                        );
                    }

                    break;
                }
                case "deleteNews": {
                    CrudNews crudNews = new CrudNews();
                    crudNews.deleteById(Integer.valueOf(request.getParameter("id")));
                    break;
                }
                case "createUser": {
                    CrudUser crudUser = new CrudUser();
                    if(request.getParameter("id")!=null){
                        crudUser.update(
                                Integer.valueOf(request.getParameter("id")),
                                request.getParameter("username"),
                                request.getParameter("password")
                        );
                    }
                    else{
                        crudUser.addUser(new User(
                                request.getParameter("username"),
                                request.getParameter("password"))
                        );
                    }
                   break;
                }
                case "deleteUser": {
                    CrudUser crudUser = new CrudUser();
                    crudUser.deleteById(Integer.valueOf(request.getParameter("id")));
                    break;
                }
            }
        }

        doGet(request, response);
    }
}
