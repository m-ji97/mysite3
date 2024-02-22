package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/web_db";
	private String id = "web";
	private String pw = "web";

	// 연결
	public void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);

		}
	}

	// 종료
	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 회원가입
	public int userInsert(UserVo uservo) {
		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into users ";
			query += " values(null,?,?,?,?)";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, uservo.getId());
			pstmt.setString(2, uservo.getPw());
			pstmt.setString(3, uservo.getName());
			pstmt.setString(4, uservo.getGender());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록되었습니다.");

		} catch (SQLException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		}
		this.close();

		return count;
	}

	public UserVo selectUserByIdPw(UserVo uservo) {
		UserVo authUser=null;

		this.getConnection();

		try {
	
			// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query += " select 	no, ";
			query += " 			name ";
			query += " from users ";
			query += " where id=? ";
			query += " and password=? ";
	
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, uservo.getId());
			pstmt.setString(2, uservo.getPw());
	
			//실행
			rs = pstmt.executeQuery();
	
			// 4.결과처리
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				authUser = new UserVo();
				authUser.setNo(no);
				authUser.setName(name);
			}
	
		} catch (SQLException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} 
		this.close();
	
		return authUser;
	}
}