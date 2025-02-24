package dc.human.kimbanbagi.tableJava.dao;

import dc.human.kimbanbagi.tableJava.dto.*;
import dc.human.kimbanbagi.tableJava.common.DBConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAO {
	private Connection conn;
	
	public boolean addRestaurant(RestaurantDTO restaurant) {
			    java.util.Date now = new java.util.Date();
		        Date sqlDate = new Date(now.getTime());
		        
		        boolean result = false;

		        try {
		        	conn = DBConnectionManager.getConnection();
		        	
		        	String sql = "INSERT into restaurants VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		        		
		        	PreparedStatement pstmt = conn.prepareStatement(sql);
		             
		             pstmt.setString(1, restaurant.getR_id());
		             pstmt.setString(2, restaurant.getR_name());
		             pstmt.setString(3, restaurant.getR_head());
		             pstmt.setString(4, restaurant.getR_city());
		             pstmt.setString(5, restaurant.getR_address());
		             pstmt.setString(6, restaurant.getR_number());
		             pstmt.setBinaryStream(7, restaurant.getR_photo());
		             pstmt.setString(8, restaurant.getU_id());
		             pstmt.setString(9, restaurant.getR_status());
		             pstmt.setString(10, restaurant.getW_status());
		             pstmt.setDate(11, sqlDate);
		             pstmt.setString(12, restaurant.getU_id());
		             pstmt.setDate(13, sqlDate);
		             pstmt.setString(14, restaurant.getU_id());
		            
		             pstmt.executeUpdate();
		             
		             changeRStatus(restaurant.getU_id());
		             
		             result=true;
		             
		             conn.close();
		 			 pstmt.close();
		 			
		             } catch (Exception e) {
		            	 e.printStackTrace();
		        }
		        
		        return result;
		    }
	
	// 식당 등록을 완료한 뒤에 user 테이블의 store_register 값을 바꿔주는 메소드
	public void changeRStatus(String id) {
		try {
			conn=DBConnectionManager.getConnection();
			
			String sql = "UPDATE users SET store_register=? WHERE user_id=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "1");
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 식당 상세 페이지
	//식당 상세 정보를 모두 가져오는 메소드
	public RestaurantDTO getRestaurantDetail(String id, String name) {
		RestaurantDTO dto = new RestaurantDTO();
		
		try {
			conn = DBConnectionManager.getConnection();
			
			String sql = "SELECT * FROM restaurants WHERE restaurant_id='" + id + "'";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setR_name(rs.getString("restaurant_name"));
				dto.setR_head(rs.getString("restaurant_head"));
				dto.setR_city(rs.getString("restaurant_city"));
				dto.setR_address(rs.getString("restaurant_address"));
				dto.setR_number(rs.getString("restaurant_number"));
				dto.setR_status(rs.getString("reservation_available"));
				dto.setW_status(rs.getString("waiting_available"));
			}
			
			conn.close();
			pstmt.close();
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	// 마이 페이지 > 사용한 식당 내역
	//사용자가 이용 완료한 식당 리스트 가져오는 메소드
	public BookDTO restaurantHistory(String id) {
		BookDTO dto = new BookDTO();
		
		try {
			conn = DBConnectionManager.getConnection();
			
			String sql = "SELECT * FROM reservation WHERE user_id='" + id + "'";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setRestaurant_name(rs.getString("restaurant_name"));
				dto.setA_count(rs.getString("adult_count"));
				dto.setK_count(rs.getString("kids_count"));
				dto.setStatus(rs.getString("reservation_status"));
			}
			
			conn.close();
			pstmt.close();
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	// 사장님 메인 화면
	//사장님 메인 화면에 본인이 등록한 가게 정보 뜨게 하는 메소드
	public RestaurantDTO getOwnerRestaurant(String id) {
		RestaurantDTO dto = new RestaurantDTO();
		
		try {
			conn = DBConnectionManager.getConnection();
			
			String sql = "SELECT * FROM restaurants WHERE restaurant_id='" + id + "'";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setR_id(rs.getString("restaurant_id"));
				dto.setR_name(rs.getString("restaurant_name"));
				dto.setR_head(rs.getString("restaurant_head"));
				dto.setR_city(rs.getString("restaurant_city"));
				dto.setR_address(rs.getString("restaurant_address"));
				dto.setR_number(rs.getString("restaurant_number"));
				dto.setR_status(rs.getString("reservation_available"));
				dto.setW_status(rs.getString("waiting_available"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	// 사용자 메인 화면
	//사용자 위치 기반 주위 식당 정보를 뜨게 하는 메소드
	public void restaurantAround() {
		
	}
}
