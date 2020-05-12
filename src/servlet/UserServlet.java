package servlet;

import dao.UserDAO;
import dao.UserDAOImpl;
import pojo.User;
import util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
//	private UserDAO userDAO= new UserDAOImpl();

    private static final long serialVersionUID = 1L;

//    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
//        return null;
//    }
//
//    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
//        return null;
//    }
//
//    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
//        return null;
//    }
//
//    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
//        return null;
//    }
    public UserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String flag = req.getParameter("flag");
        String uid = req.getParameter("uid");

        if(flag != null && flag.equals("Reset")) {
            if(uid != null) {
                UserDAO userDAO = new UserDAOImpl();
                List<User> users = userDAO.list();
                for (User user : users) {
                    if(user.getId() == Integer.parseInt(uid)) user.setPassword("123456");
                }
            }
        } else {
            UserDAO userDAO = new UserDAOImpl();
            List<User> users = userDAO.list();
            req.setAttribute("users", users);
            req.getRequestDispatcher("admin/listUser.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

//    public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
//    	List<User> users = userDAO.list(page.getStart(), page.getCount());
//        int total = userDAO.getTotal();
//        page.setTotal(total);
//        request.setAttribute("users", users);
//        request.setAttribute("page", page);
//        return "admin/listUser.jsp";
//    }
}
