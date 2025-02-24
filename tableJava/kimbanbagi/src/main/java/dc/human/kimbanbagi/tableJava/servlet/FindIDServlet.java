package dc.human.kimbanbagi.tableJava.servlet;

import dc.human.kimbanbagi.tableJava.dao.*;
import dc.human.kimbanbagi.tableJava.dto.*;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/findID")
public class FindIDServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		
		UserDTO dto = new UserDTO();
		dto.setEmail(email);
		dto.setName(name);
		
		FindIDDAO dao = new FindIDDAO();
		String userId = dao.findID(dto);
		
		
		if(userId != null) {
			request.setAttribute("userId", userId);
			request.getRequestDispatcher("findID_OK.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("findID_NO.jsp").forward(request, response);
		}
		
	}
	
	
}
