package dc.human.kimbanbagi.tableJava.dao;

import java.sql.Connection;
import java.sql.*;
import java.util.List;

import dc.human.kimbanbagi.tableJava.common.DBConnectionManager;
import dc.human.kimbanbagi.tableJava.dto.WaitDTO;

import java.util.ArrayList;

public class WaitDAO {
	private Connection conn;
	
	public void addWaiting(WaitDTO wait) {
		
		try {
			conn = DBConnectionManager.getConnection();
			
			String sql = "INSERT INTO waiting(user_id, phone_number, restaurant_id, restaurant_name, head_count, waiting_number, waiting_status, created_date, created_id, updated_date, updated_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, wait.getUserId());
			pstmt.setString(2, wait.getPhoneNumber());
			pstmt.setString(3, wait.getRestaurantId());
			pstmt.setString(4, wait.getRestaurantName());
			pstmt.setInt(5, wait.getHeadCount());
			pstmt.setInt(6, wait.getWaitingNumber());
			pstmt.setString(7, wait.getWaitingStatus());
			pstmt.setString(8, wait.getCreatedDate());
			pstmt.setString(9, wait.getCreatedId());
			pstmt.setString(10, wait.getUpdatedDate());
			pstmt.setString(11, wait.getUpdatedId());
			
			pstmt.executeUpdate();
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getNextWaitingNumber(String restaurantId) {
		int nextNumber = 1;
		
        try {
        	conn = DBConnectionManager.getConnection();
        	
            String sql = "SELECT MAX(waiting_number) FROM waiting WHERE restaurant_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                nextNumber = rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextNumber;
	 }

	public List<WaitDTO> getWaitingList(String restaurantId) {
        List<WaitDTO> waitingList = new ArrayList<>();
        try {
        	
        	conn = DBConnectionManager.getConnection();
        	
            String sql = "SELECT * FROM waiting WHERE restaurant_id = ? ORDER BY waiting_number";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                WaitDTO wait = new WaitDTO();
                wait.setUserId(rs.getString("user_id"));
                wait.setPhoneNumber(rs.getString("phone_number"));
                wait.setRestaurantId(rs.getString("restaurant_id"));
                wait.setRestaurantName(rs.getString("restaurant_name"));
                wait.setHeadCount(rs.getInt("head_count"));
                wait.setWaitingNumber(rs.getInt("waiting_number"));
                wait.setWaitingStatus(rs.getString("waiting_status"));
                wait.setCreatedDate(rs.getString("created_date"));
                wait.setCreatedId(rs.getString("created_id"));
                wait.setUpdatedDate(rs.getString("updated_date"));
                wait.setUpdatedId(rs.getString("updated_id"));

                waitingList.add(wait);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return waitingList;
    }
	
	
}
