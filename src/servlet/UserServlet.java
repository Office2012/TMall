package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.User;
import dao.UserDAO;
import dao.UserDAOImpl;

/**
 * Servlet implementation class UserServlet
 */
//userServelt_reset  userServlet_list  struts框架
@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAOImpl();


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		/*
		 * String flag = request.getParameter("flag"); if (flag != null &&
		 * flag.equals("reset")) { //TODO 处理重置密码请求 String
		 * uid=request.getParameter("uid"); } else { List<User> users = userDAO.list();
		 * request.setAttribute("users", users);
		 * request.getRequestDispatcher("admin/listUser.jsp").forward(request,
		 * response); }
		 * request.getRequestDispatcher("admin/listUser.jsp").forward(request,
		 * response);
		 */
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
	
	
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}
}
