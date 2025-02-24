package dc.human.kimbanbagi.tableJava.servlet;

import dc.human.kimbanbagi.tableJava.dao.*;
import dc.human.kimbanbagi.tableJava.dto.*;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		// 중복 확인 버튼을 눌렀을 경우
        if ("idCheck".equals(action)) {
            String id = request.getParameter("userID");
            JoinDAO jdao = new JoinDAO();
            
            boolean idCheck = jdao.idCheck(id);

            if (idCheck) {
                response.getWriter().write("중복된 아이디입니다.");
            } else {
                response.getWriter().write("사용 가능한 아이디입니다.");
            }
            
         // 회원 가입 버튼을 눌렀을 경우
        } else if ("join".equals(action)) {
            String id = request.getParameter("userID");
            String pwd = request.getParameter("userPW");
            String email = request.getParameter("userEmail");
            String name = request.getParameter("userName");
            String number = request.getParameter("userNumber");
            String role = request.getParameter("role");
	
			if (role.equals("customer")) {
				role = "1";
			} else {
				role = "2";
			}
			
			UserDTO dto = new UserDTO();
			
            dto.setuId(id);
            dto.setPwd(pwd);
            dto.setEmail(email);
            dto.setName(name);
            dto.setNumber(number);
            dto.setRole(role);
            dto.setRegister("0");
            dto.setWithdrawal("1");
			
			
			JoinDAO dao = new JoinDAO();
			dao.join(dto);
			
			request.getRequestDispatcher("joinSuccess.jsp").forward(request, response);
        }
    }
}
