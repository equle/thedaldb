package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import dto.Thedaldto;
import main.TheDalClass;

public class DBClass {

	public static Connection condb() throws Exception {
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver"); // mysql 설치 여부
		conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PW); // 연결 (주소, 사용자 이름, 비번)
//            System.out.println("연결 성공");
		return conn;
	}

	public static void conTest() {
		Connection conn = null;
		try {
			conn = DBClass.condb();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

//데이터 넣기
	public static void insert(int num1, int num2) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBClass.condb();

			String sql = "INSERT INTO numlist_tb (num1, num2) VALUES (?, ?)"; // 준비
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num1); // 데이터입력
			pstmt.setInt(2, num2);

			int count = pstmt.executeUpdate(); // db에 데이터 입력 실행 0 = 실패, 1은 성공
			if (count == 0) {
//				System.out.println("데이터 입력 실패");
			} else {
//				System.out.println("데이터 입력 성공");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 데이터 삭제
	public static void numlist_delete() {
		Connection conn = null;
		ResultSet rs = null; // 객체 값 반환 변수 ResultSet
		Statement stmt = null;
		PreparedStatement pstmt = null; // 쿼리문 ? 변환 가능 변수

		try {
			conn = DBClass.condb();

			String sql = "DELETE FROM numlist_tb";

			pstmt = conn.prepareStatement(sql); // db 연결/전달 개체 생성
			int count = pstmt.executeUpdate(); // db에 데이터 입력 실행 0 = 실패, 1은 성공
			if (count == 0) {
				System.out.println("데이터 삭제 실패");
			} else {
//				System.out.println("데이터 삭제 성공");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close(); // db 연결/전달 개체 생성
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close(); // prepareStatement ? 변수 넣기 가능하게
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close(); // prepareStatement ? 변수 넣기 가능하게
				}
				if (rs != null && !rs.isClosed()) {
					rs.close(); // 전달된 select값 받은 변수
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// numlist 데이터 가져오기
	public static ArrayList<Thedaldto> numlist_select() {
		Connection conn = null;
		ResultSet rs = null; // 객체 값 반환 변수 ResultSet
		PreparedStatement pstmt = null; // 쿼리문 ? 변환 가능 변수
		TheDalClass nc = new TheDalClass();
		ArrayList<Thedaldto> list = new ArrayList<Thedaldto>();

		try {
			conn = DBClass.condb();

			String sql = "SELECT * FROM numlist_tb";
			String sqlw = "SELECT * FROM food_tb WHERE number = ?";

			pstmt = conn.prepareStatement(sql); // db 연결/전달 개체 생성
			rs = pstmt.executeQuery(); // executeQuery select구문 사용할때 쓰는 전달 함수생성 변수

			while (rs.next()) {
				Thedaldto tddto = new Thedaldto();
				tddto.setDbnum1(rs.getInt("num1"));
				tddto.setDbnum2(rs.getInt("num2"));
				list.add(tddto);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close(); // db 연결/전달 개체 생성
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close(); // prepareStatement ? 변수 넣기 가능하게
				}
				if (rs != null && !rs.isClosed()) {
					rs.close(); // 전달된 select값 받은 변수
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// name point db에 넣기
	// 데이터 넣기
	public static void rank_insert(String name, int point) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBClass.condb();

			String sql = "INSERT INTO userthedal_tb (names, point) VALUES (?, ?)"; // 준비
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name); // 데이터입력
			pstmt.setInt(2, point);

			int count = pstmt.executeUpdate(); // db에 데이터 입력 실행 0 = 실패, 1은 성공
			if (count == 0) {
//				System.out.println("데이터 입력 실패");
			} else {
				System.out.println("이름 : "+ name + " 점수 : "+point);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 이름 포인트 가져와 출력
	public static void showrank() {
		Connection conn = null;
		ResultSet rs = null; // 객체 값 반환 변수 ResultSet
		PreparedStatement pstmt = null; // 쿼리문 ? 변환 가능 변수
		

		try {
			conn = DBClass.condb();

			String sql = "SELECT * FROM userthedal_tb";
			String sqlw = "SELECT * FROM userthedal_tb WHERE number = ?";

			pstmt = conn.prepareStatement(sql); // db 연결/전달 개체 생성
			rs = pstmt.executeQuery(); // executeQuery select구문 사용할때 쓰는 전달 함수생성 변수

			while (rs.next()) {
				System.out.println("이름 : "+ rs.getString("names") + " 점수 : "+rs.getInt("point"));
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close(); // db 연결/전달 개체 생성
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close(); // prepareStatement ? 변수 넣기 가능하게
				}
				if (rs != null && !rs.isClosed()) {
					rs.close(); // 전달된 select값 받은 변수
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
