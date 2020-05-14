package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 定义一个基础的Servlet
 */
public abstract class BaseServlet extends HttpServlet {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request=req; 
		request.setCharacterEncoding("utf-8");
		response=resp;
		super.service(request, response);
	}
}
