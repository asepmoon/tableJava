package dc.human.kimbanbagi.tableJava.servlet;

import java.io.IOException;

import dc.human.kimbanbagi.tableJava.dao.*;
import dc.human.kimbanbagi.tableJava.dto.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String id=request.getParameter("user_id");
		String pwd=request.getParameter("user_pwd");
		
		LoginDAO dao= new LoginDAO();
		String role = dao.match(id, pwd);
		
		// user_role이 "1"이면 사용자 / "2"이면 사장님
		if(role.equals("1")) {
			// request에 userId 저장
			request.setAttribute("userId", id);
			request.getRequestDispatcher("userMain").forward(request, response);
			
		} else if(role.equals("2")) {
			request.setAttribute("userId", id);
			
			// 사장님의 가게 등록 여부 확인 후 결과에 맞는 페이지로 이동
			if(dao.getRegister(id)) {
				String restaurantId = dao.getRid(id);
				
				request.setAttribute("restaurantId", restaurantId);
				request.getRequestDispatcher("ownerMain").forward(request, response);
				
			}else {
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			
		} else {
			System.out.println("일치하는 로그인 정보가 없습니다.");
		}
	}

}
