package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.util.WebUtil;

@WebServlet("/gsb")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("guestbookController");

		String action = request.getParameter("action");

		System.out.println(action);

		if("addlist".equalsIgnoreCase(action)) {
			System.out.println("guestbook>addlist");


			//guestbook.jsp 포워드
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");
		
		}else if("actionaddlist".equalsIgnoreCase(action)){
			System.out.println("guestbook>actionaddlist");
			
			//guestbook.jsp 포워드
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");
		}
		
		else if("deleteform".equalsIgnoreCase(action)) {
			System.out.println("guestbook>deleteform");


			//guestbook.jsp 포워드
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteForm.jsp");
		
		}else if("actiondelete".equalsIgnoreCase(action)) {
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteForm.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}