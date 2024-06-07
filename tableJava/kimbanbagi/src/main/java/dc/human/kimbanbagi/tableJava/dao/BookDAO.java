package dc.human.kimbanbagi.tableJava.dao;

import dc.human.kimbanbagi.tableJava.common.DBConnectionManager;
import dc.human.kimbanbagi.tableJava.dao.*;
import dc.human.kimbanbagi.tableJava.dto.*;
import java.util.*;
import java.io.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class BookDAO {
private Connection conn;
	
	public void addBook(BookDTO dto) {
		java.util.Date now = new java.util.Date();
        Date sqlDate = new Date(now.getTime()); 
        
		try {
			conn = DBConnectionManager.getConnection();
			
			String sql = "INSERT INTO reservation VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getRestaurant_id());;
			pstmt.setString(3, dto.getRestaurant_name());
			pstmt.setString(4, dto.getDate());
			pstmt.setString(5, dto.getTime());	
			pstmt.setString(6, dto.getStatus());
			pstmt.setString(7, dto.getA_count());
			pstmt.setString(8, dto.getK_count());
			pstmt.setString(9, dto.getS_count());
			pstmt.setString(10, dto.getW_count());
			pstmt.setString(11, dto.getRequirement());
			
			pstmt.setDate(12, sqlDate);
			pstmt.setString(13, dto.getUser_id());
			pstmt.setDate(14, sqlDate);
			pstmt.setString(15, dto.getUser_id());
			
			pstmt.executeUpdate(); 
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//사용자의 예약 리스트 가져오는 메소드
	public List<BookDTO> getUserBookList(String id) {
		List<BookDTO> bookList = new ArrayList<>();
		
		try {
			conn = DBConnectionManager.getConnection();
			
			String sql = "SELECT * FROM reservation WHERE user_id=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setRestaurant_id(rs.getString("restaurant_id"));
				dto.setRestaurant_name(rs.getString("restaurant_name"));
				dto.setDate(rs.getString("reservation_date"));
				dto.setTime(rs.getString("reservation_time"));
				switch(rs.getString("reservation_status")) {
					case "0" : dto.setStatus("예약 대기");
									break;
					case "1" : dto.setStatus("예약 확정");
									break;
					case "2" : dto.setStatus("예약 취소"); //사용자가 예약 취소
									break;
					case "3" : dto.setStatus("예약 거절"); //사장님이 예약 취소
									break;
					case "4" : dto.setStatus("예약 종료");
									break;
					}
				dto.setA_count(rs.getString("adult_count"));
				dto.setK_count(rs.getString("kids_count"));
				dto.setS_count(rs.getString("stroller"));
				dto.setW_count(rs.getString("wheelchair"));
				dto.setRequirement(rs.getString("requirements"));
				
				bookList.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return bookList;
	}
	
	
	// 사장님의 예약 리스트 가져오는 메소드
	public List<BookDTO> getOwnerBookList(String id) {
		List<BookDTO> dtoList = new ArrayList<>();
		
		try {
			conn = DBConnectionManager.getConnection();
			String sql = "SELECT * FROM reservation WHERE restaurant_id='" + id + "'";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				
				dto.setUser_id(rs.getString("user_id"));
				dto.setRestaurant_id(rs.getString("restaurant_id"));
				dto.setRestaurant_name(rs.getString("restaurant_name"));
				dto.setDate(rs.getString("reservation_date"));
				dto.setTime(rs.getString("reservation_time"));
				switch(rs.getString("reservation_status")) {
					case "0" : dto.setStatus("예약 대기");
									break;
					case "1" : dto.setStatus("예약 확정");
									break;
					case "2" : dto.setStatus("예약 취소"); //사용자가 예약 취소
									break;
					case "3" : dto.setStatus("예약 거절"); //사장님이 예약 취소
									break;
					case "4" : dto.setStatus("예약 종료");
									break;
					}
				dto.setA_count(rs.getString("adult_count"));
				dto.setK_count(rs.getString("kids_count"));
				dto.setS_count(rs.getString("stroller"));
				dto.setW_count(rs.getString("wheelchair"));
				dto.setRequirement(rs.getString("requirements"));
				
				dtoList.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dtoList;
	}
	
	
	public void changeStatus(String status, String u_id, String r_id ) {
		try {
			conn = DBConnectionManager.getConnection();
			String sql = "UPDATE reservation SET reservation_status='" + status + "' WHERE user_id='" + u_id + "' AND restaurant_id='" + r_id +"'";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
