package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserController");

		String action = request.getParameter("action");

		//user 실행되는지 확인
		System.out.println(action);

		if("joinform".equalsIgnoreCase(action)) {
			System.out.println("user>joinform");


			// 회원가입 포워드
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");

		}
		else if("join".equals(action)) {
			System.out.println("user>join");

			//파라미터에서 데이터 꺼내기
			String id = request.getParameter("id");
			String password = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");

			//데이터를 vo로 묶어준다
			UserVo userVo = new UserVo(id, password, name, gender);
			System.out.println(userVo.toString());

			// dao의 메소드로 회원가입
			UserDao userdao = new UserDao();
			userdao.userInsert(userVo);

			//joinok.jsp 포워드
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinOk.jsp");
			
		}else if("logout".equals(action)) {
			System.out.println("user>logout");
			
			HttpSession session = request.getSession();
			session.invalidate();
			
			WebUtil.forward(request, response, "http://localhost:8080/mysite3/main");
		
		}else if("loginform".equalsIgnoreCase(action)) {
			System.out.println("user>loginform");

			WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");
		
		}else if("login".equalsIgnoreCase(action)) {
			System.out.println("user>login");

			String id = request.getParameter("id");
			String password = request.getParameter("pw");

			UserVo uservo = new UserVo(id, password);

			UserDao userdao = new UserDao();
			UserVo authUser = userdao.selectUserByIdPw(uservo); //id pw
			//no name
			
			
			if(authUser != null) { //로그인성공
				HttpSession session = request.getSession();
				session.setAttribute("authUser", authUser);
				
				WebUtil.redirect(request, response, "/mysite3/main");
			}else {
				System.out.println("로그인실패");
				
				WebUtil.redirect(request, response, "/mysite3/user?action=loginform");
			}
			
		}
		/*else if("modifyform".equalsIgnoreCase(action)) {
			System.out.println("user>modifyform");

			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyForm.jsp");
		
		}else if("modify".equalsIgnoreCase(action)){
			System.out.println("user>modify");
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyForm.jsp");
		}*/
		
		else {
			System.out.println("action값을 다시확인해주세요");
		}

		//회원가입

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
