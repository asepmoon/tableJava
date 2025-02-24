package dc.human.kimbanbagi.tableJava.servlet;

import dc.human.kimbanbagi.tableJava.dao.*;
import dc.human.kimbanbagi.tableJava.dto.*;
import java.util.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/UserBookList")
public class UserBookListServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("action");
		
		BookDAO dao = new BookDAO();
		
		if(action.equals("bookList")) {
			String userId = request.getParameter("userId");
			
			List<BookDTO> bookList = dao.getUserBookList(userId);
			
			request.setAttribute("userId", userId);
			request.setAttribute("bookList", bookList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/userBookList.jsp");
			dispatcher.forward(request, response);
			
		} else if(action.equals("status")) {
			String userId = request.getParameter("userId");
			String restaurantId = request.getParameter("restaurantId");
			
			String status = request.getParameter("status");
			
			dao.changeStatus(status, userId, restaurantId);
			
			response.sendRedirect("/userBookList.jsp");
			
		} 
	}

}
