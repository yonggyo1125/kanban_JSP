package service;

import javax.servlet.http.HttpServletRequest;

import dao.KanbanDao;
import dto.KanbanDto;
import constants.Status;

/**
 * Kanan 등록 Service
 * 
 * @author YONGGYO
 *
 */
public class KanbanRegisterService {
	
	/** 
	 * 등록 처리 
	 * @param request
	 * @throws KanbanException
	 */
	public void regist(HttpServletRequest request) throws KanbanException {
		KanbanDao dao = KanbanDao.getInstance();
		String rstatus = request.getParameter("status");
		String workNm = request.getParameter("workNm");
		
		/** 유효성 검사 S */
		if (rstatus.isBlank()) {
			throw new KanbanException("작업 상태를 선택하세요.");
		}
		
		if (workNm.isBlank()) {
			throw new KanbanException("작업 내용을 입력하세요.");
		}
		
		/** 유효성 검사 E */
		
		/** 등록 처리 S */
		Status status = Enum.valueOf(Status.class, rstatus); 
		KanbanDto kanban = new KanbanDto(status, workNm);
		dao.register(kanban);
		/** 등록 처리 E */
	}
}
