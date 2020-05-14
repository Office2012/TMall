package servlet;

import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.UserDAO;
import dao.UserDAOImpl;

@WebServlet("/loginServlet")
public class LoginServlet extends BaseServlet {
    private UserDAO userDAO = new UserDAOImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String result=null;
        String flag = request.getParameter("flag");
        if (flag != null && flag.equals("reset")) {
            //处理重置密码请求 String
            update();
            result=list();
        }
        else {
            //用户列表显示
            result=list();
        }
        //response.sendRedirect(result);
        request.getRequestDispatcher(result).forward(request,response);
    }

    //处理重置密码请求
    private String update() {
        String uid=request.getParameter("uid");
        User user=userDAO.get(Integer.parseInt(uid));
        user.setPassword("123456");
        userDAO.update(user);
        return null;
    }

    //处理用户列表请求
    private String list() {
        List<User> users = userDAO.list();
        request.setAttribute("users", users);
        return "admin/listUser.jsp";
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
