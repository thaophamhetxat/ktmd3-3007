package controller;

import module.UserPerson;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Simple_Login", value = "/home")
public class UserController extends HttpServlet {
    public UserService userService = new UserService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "createUser":
                showCreateUser(request, response);
                break;
            case "editUser":
                showEditUser(request, response);
                break;
            case "deleteUser":
                deleteUser(request, response);
                break;
            default:
                home(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "createUser":
                createUser(request, response);
                break;
            case "editUser":
                editUser(request, response);
            case "findUser":
                int indexFind = Integer.parseInt(request.getParameter("search"));
                List<UserPerson> listCR = new ArrayList<>();
                for (UserPerson c : userService.listUser) {
                    if (c.getId() == indexFind) {
                        listCR.add(c);
                    }
                }
                request.setAttribute("listUser", listCR);
                requestDispatcher = request.getRequestDispatcher("views/home.jsp");
                requestDispatcher.forward(request, response);
                break;
            default:

        }
    }


    protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserPerson> userPersonList = this.userService.home();
        request.setAttribute("listUser", userPersonList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/home.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String user = request.getParameter("userName");
        String password = request.getParameter("passWord");
        String email = request.getParameter("email");


        UserPerson userPerson = new UserPerson(id, user, password, email);
        this.userService.save(userPerson);

        request.setAttribute("listUser", userService.listUser);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/createUser.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void showCreateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/home.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEdit = Integer.parseInt(request.getParameter("id"));
        String userEdit = request.getParameter("userName");
        String paswordEdit = request.getParameter("passWord");
        String emailEdit = request.getParameter("email");

        UserPerson userPersonEdit = new UserPerson(idEdit, userEdit, paswordEdit, emailEdit);

        int index = Integer.parseInt(request.getParameter("index"));
        userService.edit(userPersonEdit, index);

        request.setAttribute("listUser", userService.listUser);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/EditUser.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void showEditUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int indexEdit = Integer.parseInt(request.getParameter("index"));
        request.setAttribute("listUser", userService.listUser.get(indexEdit));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/EditUser.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        int index = Integer.parseInt(request.getParameter("index"));
        userService.delete(index);
        response.sendRedirect("/home");
    }


}

