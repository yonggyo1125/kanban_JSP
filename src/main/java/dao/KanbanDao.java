package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.KanbanDto;
import service.KanbanException;

/**
 * Kanban Dao(Data Access Object)
 * @author YONGGYO
 *
 */
public class KanbanDao {
	
	private static KanbanDao instance = new KanbanDao();
	
	private KanbanDao() {}
	
	/**
	 * 작업 등록 
	 * 
	 * @param kanban
	 */
	public void register(KanbanDto kanban) {
		String sql = "INSERT INTO `works` (`status`, workNm) VALUES (?,?)";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, kanban.getStatus().toString());
			pstmt.setString(2, kanban.getWorkNm());
			pstmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new KanbanException("작업 등록에 실패하였습니다.");
		}
	}
	
	/**
	 * Connection 객체 생성하기
	 * 
	 * @return
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/kanban?user=kanban&password=aA!123456";
			conn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * SingleTon 방식로 인스턴스 생성
	 * 
	 * @return
	 */
	public static KanbanDao getInstance() {
		if (instance == null) {
			instance = new KanbanDao();
		}
		
		return instance;
	}
}
