package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import dao.UserDAO;
import pojo.User;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDAO userDAO;
    /**
     * @Description: 列出用户信息
     */
    @RequestMapping(value = "/listUser.do")
    public String listAppointment(HttpServletRequest request) {
        List<User> users = userDAO.list();
        request.setAttribute("users", users);
        return "admin/listUser";
    }
}
