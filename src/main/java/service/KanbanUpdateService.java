package service;

import javax.servlet.http.HttpServletRequest;

import dao.KanbanDao;
import dto.KanbanDto;
import constants.Status;

/**
 * 작업내용 수정
 * 
 * @author YONGGYO
 *
 */
public class KanbanUpdateService {
	
	public void update(HttpServletRequest request) {
		KanbanDto kanban = new KanbanDto();
		
		String rid = request.getParameter("id");
		String rstatus = request.getParameter("status");
		String workNm = request.getParameter("workNm");
		
		/** 유효성 검사 S */
		if (rid == null || rid.isBlank()) {
			throw new KanbanException("잘못된 접근입니다.");
		}
		
		if (rstatus.isBlank()) {
			throw new KanbanException("작업 상태를 선택하세요.");
		}
		
		if (workNm.isBlank()) {
			throw new KanbanException("작업 내용을 입력하세요.");
		}
		/** 유효성 검사 E */
		
		/** 수정 처리 S */
		Status status = Enum.valueOf(Status.class, rstatus); 
		kanban.setId(Integer.valueOf(rid));
		kanban.setStatus(status);
		kanban.setWorkNm(workNm);
		
		KanbanDao.getInstance().update(kanban);
		/** 수정 처리 E */
	}
}
