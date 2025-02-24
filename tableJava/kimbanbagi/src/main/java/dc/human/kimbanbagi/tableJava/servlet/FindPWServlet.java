package dc.human.kimbanbagi.tableJava.servlet;

import dc.human.kimbanbagi.tableJava.dao.*;
import dc.human.kimbanbagi.tableJava.dto.*;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findPW")
public class FindPWServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("userID");
		String name = request.getParameter("userName");
		
		UserDTO dto = new UserDTO();
		dto.setuId(id);
		dto.setName(name);
		
		TempoPWDAO dao = new TempoPWDAO();
		boolean check = dao.check(dto);
		
		if(check) {
			 response.sendRedirect("findPW_done.jsp");
		} else {
			 System.out.println("일치하는 정보가 없습니다.");
		}
	}

}
