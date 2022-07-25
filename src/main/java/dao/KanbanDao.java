package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import dto.KanbanDto;
import service.KanbanException;
import constants.Status;


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
		String sql = "INSERT INTO works (status, workNm) VALUES (?,?)";
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, kanban.getStatus().toString());
			pstmt.setString(2, kanban.getWorkNm().trim());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new KanbanException("작업 등록에 실패하였습니다.");
		}
	}
	
	/**
	 * 작업 수정
	 * 
	 * @param kanban
	 */
	public void update(KanbanDto kanban) {
		String sql = "UPDATE works SET "
                       + "status = ?, "
                       + "workNm = ?, "
                       + "modDt = ? "
                       + " WHERE id = ?";
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, kanban.getStatus().toString());
			pstmt.setString(2, kanban.getWorkNm().trim());
			pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setInt(4, kanban.getId());							
			pstmt.executeUpdate();	
		
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new KanbanException("수정에 실패하였습니다.");
		}
	}
	
	/**
	 * 작업 삭제
	 * 
	 * @param kanban
	 */
	public void delete(KanbanDto kanban) {
		delete(kanban.getId());	
	}
	
	/**
	 * 작업 삭제
	 * 
	 * @param id
	 */
	public void delete(int id) {
		String sql = "DELETE FROM works WHERE id = ?";
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new KanbanException("삭제에 실패하였습니다.");
		}
	}
	
	/**
	 * 작업목록 
	 * 
	 * @return
	 */
	public List<KanbanDto> gets() {
		
		return gets(null);
	}
	
	/**
	 * 작업 목록 
	 * 
	 * @param status 작업 상태
	 * @return
	 */
	public List<KanbanDto> gets(Status status) {
		List<KanbanDto> list = null;
		
		StringBuffer sb  = new StringBuffer("SELECT * FROM works ");
		if (status != null) {
			sb.append("WHERE status = ? ");
		}
		sb.append("ORDER BY id DESC");
		
		String sql = sb.toString();
		
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			list = new ArrayList<KanbanDto>();
			while(rs.next()) {
				KanbanDto kanban = new KanbanDto();
				kanban.setId(rs.getInt("id"));
				kanban.setStatus(Enum.valueOf(Status.class, rs.getString("status")));
				kanban.setWorkNm(rs.getString("workNm"));
				kanban.setRegDt(rs.getTimestamp("regDt").toLocalDateTime());
				Timestamp modDt = rs.getTimestamp("modDt");
				if (modDt != null) {
					kanban.setModDt(modDt.toLocalDateTime());
				}
				
				list.add(kanban);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return list;
	}
	
	/**
	 * 작업 내역 조회
	 * 
	 * @param id
	 * @return
	 */
	public KanbanDto get(int id) {
		KanbanDto kanban = new KanbanDto();
		String sql = "SELECT * FROM works WHERE id = ?";
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				kanban.setId(rs.getInt("id"));
				kanban.setStatus(Enum.valueOf(Status.class, rs.getString("status")));
				kanban.setWorkNm(rs.getString("workNm"));
				kanban.setRegDt(rs.getTimestamp("regDt").toLocalDateTime());
				Timestamp modDt = rs.getTimestamp("modDt");
				if (modDt != null) {
					kanban.setModDt(modDt.toLocalDateTime());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return kanban;
	}
	public KanbanDto get(String id) {
		if (id == null) {
			return null;
		}
		
		return get(Integer.valueOf(id));
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
